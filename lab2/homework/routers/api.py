import base64
import json
from datetime import datetime, timedelta
from typing import Annotated

import numpy as np
from fastapi import APIRouter, Depends, status, HTTPException, Form
from fastapi.responses import HTMLResponse
from fastapi import Request
from redis.asyncio.client import Redis

from depends.auth import get_current_active_user
from depends.db import get_redis
from depends.models import User
import aiohttp

from depends.templates import templates

router = APIRouter(
    prefix="/api",
    tags=["api"],
    responses={status.HTTP_401_UNAUTHORIZED: {
    }}
)

import os
from dotenv import load_dotenv

load_dotenv()

ACCUWEATHER_API_KEY = os.getenv("ACCUWEATHER_API_KEY")
USE_CACHE = os.getenv("USE_CACHE") == "True"

async def load_accuweather_cached(redis: Redis, query: dict, url: str, api_key: str, session: aiohttp.ClientSession):
    redis_key = f"{{accuweather_query}}{json.dumps({'query': query, 'url': url})}"
    if USE_CACHE:
        cached = await redis.get(redis_key)
        if cached is not None:
            return json.loads(cached)
    async with session.get(url, params={"apikey": api_key, **query}) as response:
        if response.status != 200:
            return None
        expires = response.headers.get("Expires")
        if expires is not None:
            try:
                expires = datetime.strptime(expires, "%a, %d %b %Y %H:%M:%S %Z")
            except:
                expires = datetime.now() + timedelta(days=1)
        else:
            expires = datetime.now() + timedelta(days=1)
        data = await response.json()
        if USE_CACHE:
            await redis.set(redis_key, json.dumps(data), exat=expires)
        return data


@router.get("/weather", response_class=HTMLResponse)
async def get_weather(city: str, request: Request, user: User = Depends(get_current_active_user),
                      redis: Redis = Depends(get_redis)):
    error = HTTPException(
        status_code=status.HTTP_502_BAD_GATEWAY,
        detail="Could not retrieve data from upstream service"
    )
    async with aiohttp.ClientSession() as session:
        locations = await load_accuweather_cached(
            redis,
            {"q": city},
            "https://dataservice.accuweather.com/locations/v1/cities/search",
            ACCUWEATHER_API_KEY,
            session
        )
        if locations is None or len(locations) == 0:
            raise error
        location = locations[0]
        location_key = location["Key"]
        geo_position = location["GeoPosition"]

        forecast = await load_accuweather_cached(
            redis,
            {"metric": "true"},
            f"https://dataservice.accuweather.com/forecasts/v1/daily/5day/{location_key}",
            ACCUWEATHER_API_KEY,
            session
        )
        if forecast is None:
            raise error
        series = forecast["DailyForecasts"]
        series = [
            {
                **serie,
                "avgTemp": (serie['Temperature']['Minimum']['Value'] + serie['Temperature']['Maximum']['Value']) / 2
             } for serie in series]

        average_temp = np.average([s['avgTemp'] for s in series])
        max_temp = max(series, key=lambda x: x['avgTemp'])
        min_temp = min(series, key=lambda x: x['avgTemp'])

        async with session.get(
            "http://www.7timer.info/bin/api.pl", params={
                    "lon": geo_position['Longitude'],
                    "lat": geo_position['Latitude'],
                    "product": "civil",
                    "output": "json"
                }) as response:
            if response.status != 200:
                raise error
            timer_forecast = await response.content.read()

        timer_series = json.loads(timer_forecast)["dataseries"]
        timer_average_temp = np.average([s['temp2m'] for s in timer_series])
        timer_max_temp = max(timer_series, key=lambda x: x['temp2m'])
        timer_min_temp = min(timer_series, key=lambda x: x['temp2m'])


    return templates.TemplateResponse("forecast.html", {
        "request": request,
        "min_temp_when": min_temp["Date"],
        "min_temp": min_temp["avgTemp"],
        "max_temp_when": max_temp["Date"],
        "max_temp": max_temp["avgTemp"],
        "avg_temp": average_temp,
        "username": user.username,
        "location_name": location["LocalizedName"],
        "lat": geo_position["Latitude"],
        "long": geo_position["Longitude"],

        "timer_min_temp_when": timer_min_temp["timepoint"],
        "timer_min_temp": timer_min_temp["temp2m"],
        "timer_max_temp_when": timer_max_temp["timepoint"],
        "timer_max_temp": timer_max_temp["temp2m"],
        "timer_avg_temp": timer_average_temp,

    })
#
# @router.get("/users/me")
# async def read_users_me(current_user: User = Depends(get_current_active_user)):
#     return current_user

import os
from contextlib import asynccontextmanager

import redis.asyncio as redis
from dotenv import load_dotenv
from fastapi import FastAPI, Request

from depends.templates import templates
# from starlette.middleware.sessions import SessionMiddleware
from routers.api import router as router_api
from routers.auth import router as router_auth
from fastapi.responses import HTMLResponse

load_dotenv()

REDIS_URL = os.getenv("REDIS_URL")


@asynccontextmanager
async def lifespan(app: FastAPI):
    app.redis = redis.from_url(REDIS_URL)
    yield
    await app.redis


app = FastAPI(lifespan=lifespan)


@app.get("/", response_class=HTMLResponse)
async def root(request: Request):
    return templates.TemplateResponse("root.html", {
        "request": request
    })


app.include_router(router_api)
app.include_router(router_auth)

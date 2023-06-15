import asyncio
import os

from depends.auth import hash_password
from depends.db import update_user
from depends.models import UserInDB
from dotenv import load_dotenv
from redis.asyncio import Redis
import sys
load_dotenv()
REDIS_URL = os.getenv("REDIS_URL")




async def add_user(user: UserInDB):
    async with Redis.from_url(url=REDIS_URL) as redis:
        await update_user(user, redis)


if __name__ == '__main__':
    username = "johndoe"
    password = "secret"
    if len(sys.argv) == 3:
        username = sys.argv[1]
        password = sys.argv[2]
    user = UserInDB(**{
        "username": username,
        "full_name": "<N/A>",
        "email": f"{username}@example.com",
        "hashed_password": hash_password(password),
        "disabled": False,
    })
    loop = asyncio.new_event_loop()
    try:
        task = loop.create_task(add_user(user))
        loop.run_until_complete(task)
    finally:
        loop.close()

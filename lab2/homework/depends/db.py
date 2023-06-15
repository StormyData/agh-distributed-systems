from fastapi.requests import Request
from redis.asyncio import Redis

from depends.models import UserInDB


async def get_redis(request: Request):
    return request.app.redis


async def update_user(user: UserInDB, redis: Redis):
    serialized = user.json()
    await redis.set(f"{{user}}{user.username}", serialized)


async def get_user(username: str, redis: Redis) -> UserInDB | None:
    serialized = await redis.get(f"{{user}}{username}")
    if serialized is not None:
        return UserInDB.parse_raw(serialized)

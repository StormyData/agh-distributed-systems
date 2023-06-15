from datetime import datetime, timedelta

from fastapi import APIRouter, Depends, status, HTTPException
from fastapi.security import OAuth2PasswordRequestFormStrict
from jose import jwt
from redis.asyncio.client import Redis

from depends.auth import JWT_TOKEN_SECRET_KEY, JWT_TOKEN_ALGORITHM, JWT_ACCESS_TOKEN_EXPIRE_MINUTES, verify_password
from depends.db import get_user, get_redis
from depends.models import Token

router = APIRouter(
    prefix="/auth",
    tags=["auth"]
)


async def authenticate_user(username: str, password: str, redis: Redis):
    user = await get_user(username, redis)
    if not user:
        return None
    if not verify_password(password, user.hashed_password):
        return None
    return user


def create_access_token(data: dict, expires_delta: timedelta | None = None):
    to_encode = data.copy()
    now = datetime.utcnow()

    if  expires_delta is None:
        expires_delta = timedelta(minutes=15)

    to_encode.update({"exp": now + expires_delta, "iat": now})
    encoded_jwt = jwt.encode(to_encode, JWT_TOKEN_SECRET_KEY, algorithm=JWT_TOKEN_ALGORITHM)
    return encoded_jwt


@router.post("/token", response_model=Token)
async def login_for_access_token(form_data: OAuth2PasswordRequestFormStrict = Depends(), redis: Redis = Depends(get_redis)):
    user = await authenticate_user(form_data.username, form_data.password, redis)
    if not user:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Incorrect username or password",
            headers={"WWW-Authenticate": "Bearer"},
        )
    access_token_expires = timedelta(minutes=JWT_ACCESS_TOKEN_EXPIRE_MINUTES)
    access_token = create_access_token(
        data={"sub": user.username}, expires_delta=access_token_expires
    )
    return {"access_token": access_token, "token_type": "bearer"}

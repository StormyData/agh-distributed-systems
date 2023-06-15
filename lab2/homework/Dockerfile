FROM python:3.10

WORKDIR /code

COPY ./requirements.txt /code/requirements.txt

RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt

#COPY ./static /code/static/

COPY ./templates/ /code/templates/
COPY ./routers/ /code/routers/
COPY ./depends/ /code/depends/

COPY ./main.py /code/

ENV USE_CACHE=""
ENV ACCUWEATHER_API_KEY=""
ENV REDIS_URL=""
ENV JWT_TOKEN_SECRET_KEY=""

EXPOSE 80

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "80"]

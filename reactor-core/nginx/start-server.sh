#!/bin/bash

docker stop webserver
docker build -t webserver .
docker run -it --rm -d -p 8091:80 --name webserver webserver
docker container logs -f webserver
#!/bin/sh
mvn clean package && docker build -t han.dea/spotitube .
docker rm -f spotitube || true && docker run -d -p 8080:8080 -p 4848:4848 --name spotitube han.dea/spotitube 

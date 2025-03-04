#!/usr/bin/env bash

export IMAGE=$1 
export IMAGE_TAG=$2

docker-compose -f docker-compose.yaml up --detach

echo "success"

docker images --format "{{.Repository}}:{{.Tag}} {{.ID}}" | grep "^""" + "${IMAGE}" + """: " | grep -v "${IMAGE_TAG}" | awk '{print \$2}' | xargs -r docker rmi -f

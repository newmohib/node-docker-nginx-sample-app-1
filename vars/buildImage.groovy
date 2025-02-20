#!/usr/bin/env groovy

def call(){
    echo 'building the docker image...'
    sh 'node -v && npm i && docker -v && docker images && docker ps -a'
    //sh 'docker build -t newmohib/node-docker-nginx-sample-app:node-1.0.2 .'
}

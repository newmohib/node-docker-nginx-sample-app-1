#!/usr/bin/env groovy

package com.example.Docker

def call(String imageName){
    return new Docker(this).buildDockerImage(imageName)
    // echo "building the docker image $imageName"
    // sh "node -v && npm i && docker -v && docker images && docker ps -a"
    // sh "docker build -t $imageName ."
}

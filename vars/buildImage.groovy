#!/usr/bin/env groovy

def call(String imageName){
    def docker = new Docker(this)
    return docker.buildDockerImagePush(imageName)
    // echo "building the docker image $imageName"
    // sh "node -v && npm i && docker -v && docker images && docker ps -a"
    // sh "docker build -t $imageName ."
}

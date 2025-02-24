#!/usr/bin/env groovy

def call(){
    def docker = new Docker(this)
    return docker.dockerHubLogin(imageName)
}

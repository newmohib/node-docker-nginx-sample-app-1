#!/usr/bin/env groovy


def call(String imageName){
    def docker = new Docker(this)
    return docker.buildDockerImage2(imageName)
}

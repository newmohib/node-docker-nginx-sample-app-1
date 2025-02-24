#!/usr/bin/env groovy

// def call(){
//      echo 'Testing the application...'
// }

def call(String imageName){
    def docker = new Docker(this)
    return docker.buildDockerImagePush(imageName)
}

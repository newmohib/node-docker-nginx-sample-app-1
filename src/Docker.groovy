#!/usr/bin/env groovy

// package com.example

class Docker implements Serializable {
    def script
    Docker(script) {
        script.echo 'Initializing Docker class...'
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "Building Docker image $imageName"
        script.sh "node -v && npm i && docker -v && docker images && docker ps -a"
        script.sh "docker build -t $imageName ."
        script.echo "Logging in to docker hub... for image $imageName"
        withCredentials([usernamePassword(credentialsId:'docker-hub-personal-credential',passwordVariable:'PASS', usernameVariable:'USER')])
        {
            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"
        }
    }
    def buildDockerImage2(String imageName) {
        script.echo "Building Docker image 2 $imageName"
       
    }


    def pushImageToDockerHub(String imageName) {
        script.echo "Pushing the docker image to docker hub... as imageName: $imageName"
        script.sh "docker push $imageName"
    }
}
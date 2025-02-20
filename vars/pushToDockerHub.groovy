#!/usr/bin/env groovy

def call(){
    echo 'Pushing the docker image to docker hub...'
    // withCredentials([usernamePassword(credentialsId:'docker-hub-personal-credential',passwordVariable:'PASS', usernameVariable:'USER')]){
    //   sh 'echo $PASS | docker login -u $USER --password-stdin'
    //   sh 'docker push newmohib/node-docker-nginx-sample-app:node-1.0.2'
    // }
}
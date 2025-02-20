#!/usr/bin/env groovy

def call(){
    echo 'installing the application...'
    sh 'node -v && npm i'
}


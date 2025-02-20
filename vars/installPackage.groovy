#!/usr/bin/env groovy

def call(){
    echo "installing the application for brnach: $BRANCH_NAME"
    sh 'node -v && npm i'
}


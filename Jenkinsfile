#!/usr/bin/env groovy


@Library('jenkins-shared-library') _
// def gv

pipeline {
    agent any
    tools {
        nodejs 'Nodejs-22.14.0'
    }
    stages {
        stage("init app") {
            steps {
                script {
                    echo 'Initializing application and loading script.groovy'
                    //gv = load "script.groovy"
                }
            }
        }
        stage("install package") {
            steps {
                script {
                    installPackage()
                }
            }
        }
        stage("test build") {
            steps {
                script {  // Wrap inside script block
                    testBuildApp()
                }
            }
        }
        stage("Build Image") {
            steps {
                script {
                    
                    buildImage('newmohib/node-docker-nginx-sample-app:node-1.0.4')
                    // buildImageForPush('newmohib/node-docker-nginx-sample-app:node-1.0.4')
                    pushToDockerHub('newmohib/node-docker-nginx-sample-app:node-1.0.4') 
                }
            }
        }
        stage("deploy") {
            steps {
                script {  // Wrap inside script block
                    deployApp()
                }
            }
        }
    }
}

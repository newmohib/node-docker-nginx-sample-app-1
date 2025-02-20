#!/usr/bin/env groovy


@Library('jenkins-shared-library')
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
                   // installPackage()
                }
            }
        }
        stage("test") {
            steps {
                script {  // Wrap inside script block
                  //  testApp()
                }
            }
        }
        stage("Build Image") {
            steps {
                script {
                    // buildImage()
                    // pushToDockerHub()
                    //gv.buildImageWithCheckExistes()
                    //gv.pushToDockerHubWithCheckExistes() 
                }
            }
        }
        stage("deploy") {
            steps {
                script {  // Wrap inside script block
                  //  deployApp()
                }
            }
        }
    }
}

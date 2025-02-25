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
                     
                }
            }
        }
        
        stage('Increment Version') {
            steps {
                script {
                     echo 'incrementing the application version...'
                    // Get current version
                    def currentVersion = sh(script: "node -p \"require('./package.json').version\"", returnStdout: true).trim()

                    // Increment patch version using npm
                    sh 'npm version patch --no-git-tag-version'

                    // Get new version
                    def newVersion = sh(script: "node -p \"require('./package.json').version\"", returnStdout: true).trim()
                    env.IMAGE_NAME = "$newVersion-$BUILD_NUMBER"
                    echo "Version updated from ${currentVersion} to ${newVersion} and image tag is ${IMAGE_NAME}"
                }
            }
        }
        stage('Commit & Push Changes') {
            steps {
                script {
                    sh 'git config --global user.name "Jenkins"'
                    sh 'git config --global user.email "jenkins@example.com"'
                    sh 'git add package.json package-lock.json'
                    sh 'git commit -m "Bump version [skip ci]"'
                    sh 'git push origin HEAD'
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
                    testApp()
                }
            }
        }
        stage("Build Image") {
            steps {
                script {
                    
                    buildImage("newmohib/node-docker-nginx-sample-app:node-${IMAGE_NAME}")
                    pushToDockerHub("newmohib/node-docker-nginx-sample-app:node-${IMAGE_NAME}") 
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

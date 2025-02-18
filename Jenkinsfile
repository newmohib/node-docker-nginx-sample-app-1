pipeline {
  agent any
  // tools {
  //   node 'node'
  // }
  stages {
    stage("install packaage"){
      steps {
        script{
          echo 'installing the application...'
          sh 'npm install'
        }
        
      }
    }
    stage("Build Image"){
      steps {
        script{
          echo 'building the docker image...'
          withCredentials([usernamePassword(credentialsId:'docker-hub-personal-credential',passwordVariable:'PASS', usernameVariable:'USER')]){
            sh 'docker build -t newmohib/node-docker-nginx-sample-app:node-1.0.2 .'
            sh 'echo $PASS | docker login -u $USER --password-stdin'
            sh 'docker push newmohib/node-docker-nginx-sample-app:node-1.0.2'
          }
        }
        
      }
    }
    stage("test"){
      steps {
        echo 'testing the application...'
      }
    }
    stage("deploy"){
      steps {
        echo 'deploying the application...'
      }
    }
  }
}

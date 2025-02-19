def gv

pipeline {
  agent any
  tools {
    nodejs 'Nodejs-22.14.0'
  }
  stages {
    stage("init app"){
      steps {
        script{
          echo 'initializing application and load script.groovy'
          gv = load "script.groovy"
        }
        
      }
    }
    stage("install packaage"){
      steps {
        script{
          gv.installPackage()
        }
        
      }
    }
    stage("Build Image"){
      steps {
        script{
          gb.buildImage()
        }
        
      }
    }
    stage("test"){
      steps {
        gv.testApp()
        
      }
    }
    stage("deploy"){
      steps {
         gb.deployApp()
      }
    }
  }
}

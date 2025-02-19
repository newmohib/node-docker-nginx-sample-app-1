
def installPackage(){
  echo 'installing the application...'
  sh 'node -v && npm i'
}

def buildImage(){
  echo 'building the docker image...'
  sh 'node -v && npm i && docker -v && docker images && docker ps -a'
  // withCredentials([usernamePassword(credentialsId:'docker-hub-personal-credential',passwordVariable:'PASS', usernameVariable:'USER')]){
  //   sh 'docker build -t newmohib/node-docker-nginx-sample-app:node-1.0.2 .'
  //   sh 'echo $PASS | docker login -u $USER --password-stdin'
  //   sh 'docker push newmohib/node-docker-nginx-sample-app:node-1.0.2'
  // }
}

def deployApp(){
  echo 'deploying the application...'
}

def testApp(){
  echo 'testing the application...'
}


return this

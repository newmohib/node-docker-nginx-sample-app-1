
def installPackage(){
  echo 'installing the application...'
  sh 'node -v && npm i'
}

def testApp(){
  echo 'testing the application...'
}

def buildImage(){
  echo 'building the docker image...'
  sh 'node -v && npm i && docker -v && docker images && docker ps -a'
  // sh 'docker build -t newmohib/node-docker-nginx-sample-app:node-1.0.2 .'
}
def pushToDockerHub(){
  echo 'Pushing the docker image to docker hub...'
  // withCredentials([usernamePassword(credentialsId:'docker-hub-personal-credential',passwordVariable:'PASS', usernameVariable:'USER')]){
  //   sh 'echo $PASS | docker login -u $USER --password-stdin'
  //   sh 'docker push newmohib/node-docker-nginx-sample-app:node-1.0.2'
  // }
}

def deployApp(){
  echo 'deploying the application...'
}

return this

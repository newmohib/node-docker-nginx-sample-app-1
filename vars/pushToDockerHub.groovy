#!/usr/bin/env groovy

// def call(String imageName){
//     echo "Pushing the docker image to docker hub... as imageName: $imageName"
//     withCredentials([usernamePassword(credentialsId:'docker-hub-personal-credential',passwordVariable:'PASS', usernameVariable:'USER')]){
//       sh 'echo $PASS | docker login -u $USER --password-stdin'
//       sh "docker push $imageName"
//     }
// }


def call(String imageName){
    def docker = new Docker(this)
    return docker.pushImageToDockerHub(imageName)
}

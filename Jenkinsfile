

pipeline {
    agent any
    tools {
        nodejs 'Nodejs-22.14.0'
    }

    environment {
        IMAGE_NAME = "newmohib/node-docker-nginx-sample-app"
        CONTAINER_NAME = "node-docker-nginx-sample-app"
    }

    stages {

        stage('Check Remote Server') {
            steps {
                script {
                    sshagent(['aws-linux-server-2gb-ram']) {
                        
                        def remoteCommand = """
                            echo Connected successfully! &&
                            node -v &&
                            npm -v &&
                            docker -v &&
                            docker images &&
                            docker ps -a
                        """
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@18.143.98.4 '${remoteCommand}'"
                    }
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    echo "building the docker image... ${env.IMAGE_NAME}"
                    sh "docker build -t ${env.IMAGE_NAME}:jenkins-1.0.1 ."
                    sh 'node -v && npm -v && docker -v && docker images && docker ps -a'
                    // sshagent(['aws-linux-server-2gb-ram']) {
                    //     // Test SSH connection
                    //     sh "ssh -o StrictHostKeyChecking=no ec2-user@18.143.98.4 'echo Connected successfully!'"
                       
                    // }
                }
            }
        }
        

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId:'docker-hub-personal-credential',passwordVariable:'PASS', usernameVariable:'USER')]){
                    script {
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh "docker tag ${env.IMAGE_NAME}:jenkins-1.0.1 ${env.IMAGE_NAME}:jenkins-1.0.1"
                        sh "docker push ${env.IMAGE_NAME}:jenkins-1.0.1"
                    }
                }
            }
        }

        stage('Deploy Application') {
            steps {
                    script {
                       
                        // def _PORT = env.PORT ?: 8000
                        // def _ADMIN_END_PORT = env.ADMIN_END_PORT ?: 4000
                        // Store the docker command in a variable
                        // def dockerCmd = """
                        //     docker pull ${env.IMAGE_NAME}:jenkins-1.0.1
                        //     docker stop ${env.CONTAINER_NAME} || true
                        //     docker rm ${env.CONTAINER_NAME} || true
                        //     docker run -d --name ${env.CONTAINER_NAME} \\
                        //         -e MYSQL_URL=${env.MYSQL_URL} \\
                        //         -e APP_URL=${env.APP_URL} \\
                        //         -e ADMIN_APP_URL=${env.ADMIN_APP_URL} \\
                        //         -e SMTP_HOST=${env.SMTP_HOST} \\
                        //         -e SMTP_USER=${env.SMTP_USER} \\
                        //         -e SMTP_PASS=${env.SMTP_PASS} \\
                        //         -p ${_PORT}:8000 \\
                        //         -p ${_ADMIN_END_PORT}:4000 \\
                        //         ${env.IMAGE_NAME}:jenkins-1.0.1
                        // """
                        def dockerCmd = """
                            docker pull ${env.IMAGE_NAME}:jenkins-1.0.1

                            # Check if the container exists, then stop and remove it
                            if [ \$(docker ps -aq -f name=${env.CONTAINER_NAME}) ]; then
                                docker stop ${env.CONTAINER_NAME}
                                docker rm ${env.CONTAINER_NAME}
                            fi

                            # Remove all older images except the latest one
                            #docker images --format "{{.Repository}}:{{.Tag}} {{.ID}}" | grep "^${env.IMAGE_NAME}:" | grep -v "jenkins-1.0.1" | awk '{print $2}' | xargs -r docker rmi -f


                            # Run the new container
                            docker run -d --name ${env.CONTAINER_NAME} \\
                            -p 4000:4000 \\
                            ${env.IMAGE_NAME}:jenkins-1.0.1
                        """

                        sshagent(['aws-linux-server-2gb-ram']) {
                           // sh "ssh -o StrictHostKeyChecking=no ec2-user@18.143.98.4 '${dockerCmd}'"
                            sh """
                                ssh -o StrictHostKeyChecking=no ec2-user@18.143.98.4 '${dockerCmd}'
                            """
                        }
                    }
            }
        }
    }
    post {
        success {
            echo "Deployment successful! 🚀"
        }
        failure {
            echo "Deployment failed. Check logs. "
        }
    }
}



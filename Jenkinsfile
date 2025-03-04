

pipeline {
    agent any
    tools {
        nodejs 'Nodejs-22.14.0'
    }

    environment {
        IMAGE_NAME = "newmohib/node-docker-nginx-sample-app"
        // IMAGE_TAG = "jenkins-1.0.2"
        CONTAINER_NAME = "node-docker-nginx-sample-app"
        BRANCH_NAME= "dev-jenkins-2-5"
    }

    stages {
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
                    env.IMAGE_TAG = "$newVersion-$BUILD_NUMBER"
                    //env.IMAGE_TAG
                    echo "Version updated from ${currentVersion} to ${newVersion} and image tag is ${env.IMAGE_NAME}"
                }
            }
        }
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
                    sh "docker build -t ${env.IMAGE_NAME}:${env.IMAGE_TAG} ."
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
                        sh "docker tag ${env.IMAGE_NAME}:${env.IMAGE_TAG} ${env.IMAGE_NAME}:${env.IMAGE_TAG}"
                        sh "docker push ${env.IMAGE_NAME}:${env.IMAGE_TAG}"
                    }
                }
            }
        }

        stage('Deploy Application') {
            steps {
                    script {
                       echo 'Deploying the application to the remote server as EC2...'
                        def dockerCmd = """
                            docker pull ${env.IMAGE_NAME}:${env.IMAGE_TAG}

                            # Check if the container exists, then stop and remove it
                            if [ \$(docker ps -aq -f name=${env.CONTAINER_NAME}) ]; then
                                docker stop ${env.CONTAINER_NAME}
                                docker rm ${env.CONTAINER_NAME}
                            fi

                            # Remove all older images except the latest one
                            docker images --format "{{.Repository}}:{{.Tag}} {{.ID}}" | grep "^""" + env.IMAGE_NAME + """: " | grep -v "${env.IMAGE_TAG}" | awk '{print \$2}' | xargs -r docker rmi -f
                            
                            # Run the new container
                            docker run -d --name ${env.CONTAINER_NAME} \\
                                -p 4000:4000 \\
                                ${env.IMAGE_NAME}:${env.IMAGE_TAG}
                            
                            # Prune all unused images, containers, and volumes
                            docker system prune -a -f
                        """

                        // docker-compose
                        def shellCmd = "bash ./server-cmds.sh ${env.IMAGE_NAME} ${env.IMAGE_TAG}"
                        def ec2Instance =  "ec2-user@18.143.98.4"
                        sshagent(['aws-linux-server-2gb-ram']) {
                            // this sh and yaml file will be copied to the remote server
                            sh "scp server-cmds.sh ${ec2Instance}:/home/ec2-user"
                            sh "scp docker-compose.yaml ${ec2Instance}:/home/ec2-user"

                            sh """
                                ssh -o StrictHostKeyChecking=no ${ec2Instance} '${shellCmd}'
                            """
                        }
                    }
            }
        }
        stage('Commit & Push Changes to Repo') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId:'github-personal-credential-2',passwordVariable:'PASS', usernameVariable:'USER')])
                        {
                            // def currentBranch = sh(script: "git rev-parse --abbrev-ref HEAD", returnStdout: true).trim()
                            sh 'git config --global user.name "Jenkins"'
                            sh 'git config --global user.email "jenkins@example.com"'

                            sh 'git status'
                            sh 'git branch'
                            sh 'git config --list'
                            
                            sh "git remote set-url origin https://${USER}:${PASS}@github.com/newmohib/node-docker-nginx-sample-app-1.git"
                            sh 'git add package.json package-lock.json'
                            // Commit changes
                            sh 'git commit -m "Bump version [skip ci]"'
                            sh "git push origin HEAD:${env.BRANCH_NAME}"
                            
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



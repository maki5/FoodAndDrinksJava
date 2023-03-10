pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                sh 'gradle assemble'
            }
        }
         stage('Test') {
            steps {
                sh 'gradle test'
            }
        }
        stage('Build Docker Image') {
            steps {
                docker build ./ -t alexababiiyopeso/fadapp
            }
        }
        stage('Upload Docker Image') {
            steps {
                docker push alexababiiyopeso/fadapp:latest
            }
        }
    }
}
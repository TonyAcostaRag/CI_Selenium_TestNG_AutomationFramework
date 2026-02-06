pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker image') {
            steps {
                bat '''
                docker build -t selenium-maven-tests .
                '''
            }
        }

        stage('Run tests in Docker') {
            steps {
                bat '''
                docker run --rm ^
                  -v "%CD%:/workspace" ^
                  -w /workspace ^
                  selenium-maven-tests ^
                  mvn clean test
                '''
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}

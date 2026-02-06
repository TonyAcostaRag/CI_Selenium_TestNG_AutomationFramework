pipeline {
    agent any

    stages {
        stage('Run tests') {
            steps {
                bat 'docker compose up --build --abort-on-container-exit'
            }
        }
    }

    post {
        always {
            bat 'docker compose down'
        }
    }
}

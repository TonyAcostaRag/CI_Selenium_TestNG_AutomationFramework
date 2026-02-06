pipeline {
    agent {
    docker {
      image 'jenkins/jenkins'
      reuseNode true
    }
  }

    environment {
        IMAGE_NAME = "jenkins/jenkins"
        CONTAINER_NAME = "jenkins"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Test Image') {
            steps {
                echo 'Building Docker image with Maven + Chrome...'
                sh '''
                  docker build -t jenkins/jenkins .
                '''
            }
        }

        stage('Run Maven Tests') {
            steps {
                echo 'Running tests inside Docker container...'
                sh '''
                  docker run --rm \
                    --name jenkins \
                    -v $(pwd):/workspace \
                    -w /workspace \
                    jenkins/jenkins \
                    mvn clean test
                '''
            }
        }
    }

    post {
        always {
            echo 'Archiving test results...'
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'

            archiveArtifacts artifacts: '''
                target/**/*.html,
                target/**/*.png,
                target/**/*.log
            ''', allowEmptyArchive: true
        }

        success {
            echo 'Tests completed successfully'
        }

        failure {
            echo 'Tests failed'
        }
    }
}

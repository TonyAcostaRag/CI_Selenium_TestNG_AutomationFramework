pipeline {
    agent any

    environment {
        IMAGE_NAME = "selenium-tests"
        CONTAINER_NAME = "selenium-tests-run"
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
                  docker build -t ${IMAGE_NAME} .
                '''
            }
        }

        stage('Run Maven Tests') {
            steps {
                echo 'Running tests inside Docker container...'
                sh '''
                  docker run --rm \
                    --name ${CONTAINER_NAME} \
                    -v $(pwd):/workspace \
                    -w /workspace \
                    ${IMAGE_NAME} \
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
            echo '✅ Tests completed successfully'
        }

        failure {
            echo '❌ Tests failed'
        }
    }
}

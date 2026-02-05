pipeline {
    agent { dockerfile true }

    stages {
        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/**/*', allowEmptyArchive: true
        }
        failure {
            echo 'Build failed'
        }
        success {
            echo 'Build succeeded'
        }
    }
}

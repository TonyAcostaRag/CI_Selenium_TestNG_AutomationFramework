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
        script {
            if (fileExists('target')) {
                archiveArtifacts artifacts: 'target/**/*', allowEmptyArchive: true
            } else {
                echo 'No workspace available, skipping artifact archiving'
            }
        }
    }
        failure {
            echo 'Build failed'
        }
        success {
            echo 'Build succeeded'
        }
    }

}

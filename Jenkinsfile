pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Prepare Workspace') {
            steps {
                bat '''
                    echo Cleaning Allure artifacts...

                    if exist allure-results rmdir /s /q allure-results
                    if exist allure-report rmdir /s /q allure-report
                    if exist target\\allure-results rmdir /s /q target\\allure-results
                    if exist target\\allure-report rmdir /s /q target\\allure-report
                    if exist target (
                        echo Deleting target directory...
                        rmdir /s /q target
                    ) else (
                        echo target directory not found
                    )
                '''
            }
        }

        stage('Resolve Dependencies') {
            steps {
                bat 'mvn -B -q dependency:resolve'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test -DtestngFile=testng.xml'
            }
        }
    }

    post {
        always {
            allure([
                includeProperties: false,
                results: [[path: 'allure-results']]
            ])
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '''
                target/**/*.html,
                target/**/*.png,
                target/**/*.log
            ''', allowEmptyArchive: true
        }
        failure {
            echo '❌ Build failed'
        }
        success {
            echo '✅ Build succeeded'
        }
    }

}

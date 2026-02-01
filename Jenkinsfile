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
                '''
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
}

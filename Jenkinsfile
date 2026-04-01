pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'Maven  3.10'
    }

    parameters {
        string(name: 'TAGS', defaultValue: '@smoke', description: 'Cucumber Tags')
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/dinesh-hz/Selenium.git'
            }
        }

        stage('Build and Test') {
            steps {
                bat "mvn clean verify -Dcucumber.filter.tags=\"${params.TAGS}\""
            }
        }

        stage('Publish Cucumber JVM Report') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/cucumber-reports/cucumber-html-reports',
                    reportFiles: 'overview-features.html',
                    reportName: 'Cucumber JVM HTML Report'
                ])
            }
        }

         stage('Publish Extent Report') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/extent-reports',
                    reportFiles: 'extent.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }
}
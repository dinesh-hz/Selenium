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
                git 'https://github.com/dinesh-hz/runover.git'
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
                    reportDir: 'target/extent-report',   // ✅ FIXED
                    reportFiles: '*.html',
                    reportName: 'Extent Spark HTML Report'
                ])
            }
        }
    }
}
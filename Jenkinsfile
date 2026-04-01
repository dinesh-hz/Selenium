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

        stage('Test Execution') {
           steps {
             script {
            def status = bat(returnStatus: true,
                script: "mvn clean verify -Dcucumber.filter.tags=\"${params.TAGS}\"")

            if (status != 0) {
                currentBuild.result = 'UNSTABLE'
                echo "Test failed but continuing..."
            }
        }
    }
}


          post {
        always {
            publishHTML(target: [
                allowMissing: true,
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
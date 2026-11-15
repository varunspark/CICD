pipeline {
    agent any

    options {
        timeout(time: 20, unit: 'MINUTES')   // kill hung builds
        disableConcurrentBuilds()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Unit tests') {
            steps {
                sh 'mvn -B clean test'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
        }
    }
}

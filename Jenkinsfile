pipeline {
    agent any

    options {
        timeout(time: 20, unit: 'MINUTES')
        disableConcurrentBuilds()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Secret scan (gitleaks)') {
            steps {
                sh 'gitleaks detect --source . --redact --verbose --exit-code 1'
            }
        }

        stage('Unit tests') {
            steps {
                sh 'mvn -B clean test'
            }
        }

        stage('SonarQube analysis + quality gate') {
            steps {
                withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_TOKEN')]) {
                    sh '''
                        mvn -B org.sonarsource.scanner.maven:sonar-maven-plugin:5.8.0.7211:sonar \
                          -Dsonar.host.url=http://localhost:9000 \
                          -Dsonar.projectKey=demo-app \
                          -Dsonar.projectName=demo-app \
                          -Dsonar.qualitygate.wait=true
                    '''
                }
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
        }
    }
}

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
        stage('SonarCloud analysis + quality gate') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh '''
                        mvn -B org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                          -Dsonar.host.url=https://sonarcloud.io \
                          -Dsonar.organization=YOUR_ORG_KEY \
                          -Dsonar.projectKey=YOUR_PROJECT_KEY \
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

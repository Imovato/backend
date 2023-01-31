pipeline {
    agent any
    stages {
        stage ('Build Discovery') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: 'feature/refactoring_service_rent']],
                    userRemoteConfigs: [[
                        credentialsId: 'github_login',
                        url: 'https://github.com/imovato/backend/'
                    ]]])
                dir('discovery') {
                    bat 'mvn clean package -DskipTests=true'
                }
            }
        }

        stage ('Deploy Discovery') {
            steps {
                dir('discovery') {
                    deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'discovery', war: 'target/discovery-0.0.1-SNAPSHOT.war'
                }
            }
        }

        stage ('Build Rent') {
            steps {
                dir('rent') {
                    bat 'mvn clean package -DskipTests=true'
                }
            }
        }

        stage ('Unit Tests-Rent') {
            steps {
                dir('rent'){
                    bat 'mvn test'
                }
            }
        }
         stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=Imovato -Dsonar.host.url=http://localhost:9000 -Dsonar.login=fafc5fd8cda17cd9cf6e2e31bf248d294b8d0568 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
                }
            }
        }
        stage ('Deploy Rent') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'rentService', war: 'target/rent-0.0.1-SNAPSHOT.war'
            }
        }
        stage ('API Test-Rent') {
            steps {
                dir('tests-api') {
                    bat 'mvn test'
                }
            }
        }
    }
}

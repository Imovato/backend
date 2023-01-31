pipeline {
    agent any
    stages {
        stage ('Build Discovery') {
            steps {
                dir('build-discovery') {
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: 'feature/refactoring_service_rent']],
                        userRemoteConfigs: [[
                            credentialsId: 'github_login',
                            url: 'https://github.com/imovato/backend/'
                        ]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [
                            [$class: 'RelativeTargetDirectory', relativeTargetDir: 'discovery']
                        ]
                    ])
                    bat 'mvn clean package -DskipTests=true'
                }
            }
        }


        stage ('Deploy Discovery') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'discovery', war: 'target/discovery-0.0.1-SNAPSHOT.war'
            }
        }

        stage ('Build Rent') {
            steps {
                dir('build-rent') {
                    git branch: 'feature/refactoring_service_rent', credentialsId: 'github_login', url: 'https://github.com/Imovato/backend/'
                    bat 'mvn clean package -DskipTests=true'
                }
            }
        }

        stage ('Unit Tests-Rent') {
            steps {
                dir('build-rent'){
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
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=3abadb2cacee5e575d29bd0f3940da61539c3ef7 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
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
                dir('api-test-rent') {
                    git branch: 'feature/refactoring_service_rent', credentialsId: 'github_login', url: 'https://github.com/Imovato/backend/'
                    bat 'mvn test'
                }
            }
        }
    }
}

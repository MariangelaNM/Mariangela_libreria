@Library('threepoints_sharedlib') _

pipeline {
    agent any

    environment {
        CUSTOM_BRANCH = 'test'
    }

    stages {
        
        stage('Checkout') {
            steps {
                      git credentialsId: 'MN_Token', url: 'https://github.com/MariangelaNM/threepoints_devops_webserver'      }
        }
        stage('Pruebas de SAST') {
            environment {
                SCANNER_HOME = tool 'SonarScanner'
            }
            steps {
                withSonarQubeEnv(installationName: 'Sonar Local',credentialsId: 'sonar-token') {
                    sh '${SCANNER_HOME}/bin/sonar-scanner -Dsonar.projectKey=threepoints_devops_webserver -Dsonar.projectName=threepoints_devops_webserver'
                }   
            }
        }      
       
    
    }
}

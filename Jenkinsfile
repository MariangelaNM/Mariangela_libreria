@Library('threepoints_sharedlib') _

pipeline {
    agent any

    environment {
        CUSTOM_BRANCH = 'test'
    }

    stages {
        
        stage('Checkout') {
            steps {
                 git credentialsId: 'MN_Token', url: 'https://github.com/MariangelaNM/threepoints_devops_webserver'
            }
        }
        
        stage('Pruebas de SAST') {
            steps {
                script { 
                    def gitBranch = env.CUSTOM_BRANCH 
                    bat "echo La rama actual del Jenkinsfile es: ${gitBranch}"
                    bat "echo Jenkinsfile: ${env.CUSTOM_BRANCH}" 
                    def result = sonarAnalysis('threepoints_devops_webserver', gitBranch, true)
                }
            }
        }
    
    }
}

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
        
       
    
    }
}

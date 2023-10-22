@Library('threepoints_sharedlib') _

pipeline {
    agent any

    environment {
        CUSTOM_BRANCH = 'lala'
    }

    stages {
        
        stage('Checkout') {
            steps {
                echo 'Obtener codigo desde Github'
            git credentialsId: 'MN_Token', url: 'https://github.com/MariangelaNM/Mariangela_libreria'      }
            }
        }
        
stage('Pruebas de SAST') {
    steps {
        script { 
            def gitBranch = CUSTOM_BRANCH
            bat "echo La rama actual del Jenkinsfile es: ${gitBranch}"
            
            if (isUnix()) {
                sh 'echo Running SAST on Unix'
                // Add your Unix SAST script commands here
            } else {
                bat 'echo Running SAST on Windows'
                // Add your Windows SAST batch script commands here
            }
        }
    }
}

        
        stage('Build') {
            steps {
                echo 'Construcción de la imagen Docker'
                bat 'docker build --tag devops_ws .'
                
            }
        }

    }


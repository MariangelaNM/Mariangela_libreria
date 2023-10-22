@Library('threepoints_sharedlib') _

pipeline {
    agent any

    environment {
        CUSTOM_BRANCH = 'lala'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Obtener código desde GitHub'
                git credentialsId: 'MN_Token', url: 'https://github.com/MariangelaNM/Mariangela_libreria'
            }
        }

        stage('Pruebas de SAST') {
            steps {
                script {
                    def gitBranch = CUSTOM_BRANCH
                    echo "La rama actual del Jenkinsfile es: ${gitBranch}"

                    if (isUnix()) {
                        sh 'echo Running SAST on Unix'
                        // Agrega aquí tus comandos de SAST para Unix
                    } else {
                        bat 'echo Running SAST on Windows'
                        // Agrega aquí tus comandos de SAST para Windows
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
}

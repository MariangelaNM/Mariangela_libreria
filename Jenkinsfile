pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Configuración para realizar la checkout de tu repositorio (por ejemplo, usando Git).
                git credentialsId: 'MN_Token', url: 'https://github.com/MariangelaNM/Mariangela_libreria'      
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                // Carga el script sonarAnalysis.groovy y llama a la función.
                echo "---Este es un mensaje que se imprimirá en la consola de Jenkins-----"

                script {
                    def sonarAnalysisScript = load 'vars/sonarAnalysis.groovy'
                    sonarAnalysisScript()
                      echo "---Fin-----"
                }
            }
        }

       /* stage('Build') {
            steps {
                // Acciones para compilar tu proyecto.
                sh 'mvn clean install'
            }
        }

        stage('Deploy') {
            steps {
                // Acciones para desplegar tu proyecto en un entorno específico.
                sh 'deploy-script.sh'
            }
        }*/
    }
}

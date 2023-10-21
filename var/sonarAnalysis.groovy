def call(boolean abortOnFailure = false, boolean abortPipeline = false) {
    // Ejecuta el análisis de SonarQube
      environment {
                SCANNER_HOME = tool 'SonarScanner'
            }
         
                withSonarQubeEnv(installationName: 'Sonar Local',credentialsId: 'sonar-token') {
                    sh '${SCANNER_HOME}/bin/sonar-scanner -Dsonar.projectKey=threepoints_devops_webserver -Dsonar.projectName=threepoints_devops_webserver'
                }   
             

    // Espera 5 minutos con un timeout
    timeout(time: 5, unit: 'MINUTES') {
        // Comprueba el resultado del QualityGate de SonarQube
        script {
            def qualityGateStatus = waitForQualityGate()
            if (qualityGateStatus != 'OK' && abortOnFailure) {
                error("QualityGate failed.")
                if (abortPipeline) {
                    currentBuild.result = 'FAILURE'
                    error("Aborting the pipeline.")
                    return
                }
            }
        }
    }
}

return this

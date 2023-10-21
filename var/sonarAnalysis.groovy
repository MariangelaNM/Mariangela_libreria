def call(boolean abortOnFailure = false, boolean abortPipeline = false) {
    // Ejecuta el análisis de SonarQube
     withSonarQubeEnv(installationName: 'Sonar Local', credentialsId: 'sonar-token') {
        sh """
            sonar-scanner
        """
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

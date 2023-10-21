def call(boolean abortOnFailure = false, boolean abortPipeline = false) {
    // Ejecuta el análisis de SonarQube
    try {
        withSonarQubeEnv(installationName: 'Sonar Local', credentialsId: 'sonar-token') {
            def scannerHome = tool 'SonarScanner'
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=threepoints_devops_webserver -Dsonar.projectName=threepoints_devops_webserver"
        }
    } catch (Exception sonarException) {
        error("Failed to run SonarQube analysis: ${sonarException.message}")
        if (abortPipeline) {
            currentBuild.result = 'FAILURE'
            error("Aborting the pipeline.")
        }
        return
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
                }
            }
        }
    }

    return this
}

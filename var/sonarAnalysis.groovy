def call(projectKey, gitBranch, abortPipeline = false) {
    def scannerResult = 1
    def haveToExitPipeline = false

    timeout(time: 30, unit: 'SECONDS') {
          withSonarQubeEnv(installationName: 'Sonar Local', credentialsId: 'sonar-token') {
            scannerResult = bat(script: "sonar-scanner -Dsonar.projectKey=${projectKey} -Dsonar.sources=.", returnStatus: true)
        }
    }

    if (abortPipeline && scannerResult != 0) {
        haveToExitPipeline = true
    } else if (!abortPipeline) {
        // Verificar si abortar el pipeline segun nombre de la rama gitBranch
        if (gitBranch == 'master' || gitBranch.startsWith('hotfix')) {
            haveToExitPipeline = true
        }
    }

    if (haveToExitPipeline) {
        error("SonarQube scan failed with result code: ${scannerResult}")
    }

    return scannerResult
}

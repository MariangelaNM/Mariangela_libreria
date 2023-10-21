def call(projectKey, gitBranch, abortPipeline = false) {
    def scannerResult = 100 // Inicializar con algo diferente de 0
    def haveToExitPipeline = false

   /* timeout(time: 20, unit: 'SECONDS') {
        withSonarQubeEnv(installationName: 'Sonar Local', credentialsId: 'sonar-token') {
            scannerResult = bat(script: "sonar-scanner -Dsonar.projectKey=${projectKey} -Dsonar.sources=.", returnStatus: true)
        }
    }

    echo "scannerResult ${scannerResult}"
    echo "abortPipeline ${abortPipeline}"
    echo "gitBranch ${gitBranch}"

    /*if (abortPipeline && scannerResult != 0) {
        haveToExitPipeline = true
    } else if (!abortPipeline) {
        // Verificar si abortar el pipeline según el nombre de la rama gitBranch
        if (gitBranch == 'mains' || gitBranch.startsWith('hotfix')) {
            haveToExitPipeline = true
        }
    }

    echo "haveToExitPipeline ${haveToExitPipeline}"

    if (haveToExitPipeline) {
        error("SonarQube scan failed with result code: ${scannerResult}")
    }*/

    return true
}

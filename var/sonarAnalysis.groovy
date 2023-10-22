def call(projectKey, gitBranch, abortPipeline = false) {
    def scannerResult = 1
    def haveToExitPipeline = false

    node {
        stage('SonarQube Analysis') {
            if (isUnix()) {
                scannerResult = sh(script: "sonar-scanner -Dsonar.projectKey=${projectKey} -Dsonar.sources=.", returnStatus: true)
            } else {
                scannerResult = bat(script: "sonar-scanner -Dsonar.projectKey=${projectKey} -Dsonar.sources=.", returnStatus: true)
            }
        }
    }

    if (abortPipeline && scannerResult != 0) {
        haveToExitPipeline = true
    } else if (!abortPipeline) {
        if (gitBranch == 'master' || gitBranch.startsWith('hotfix')) {
            haveToExitPipeline = true
        }
    }

    if (haveToExitPipeline) {
        error("SonarQube scan failed with result code: ${scannerResult}")
    }

    return scannerResult
}

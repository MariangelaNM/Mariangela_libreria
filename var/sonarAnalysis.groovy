// Importar las clases necesarias de Jenkins
import hudson.model.BuildListener
import jenkins.model.Jenkins
import hudson.EnvVars
import hudson.FilePath

// Definir la función "call" con un parámetro booleano opcional
def call(boolean abortOnQualityGate = false) {
    // Verificar si se debe realizar un escaneo de SonarQube o solo mostrar un mensaje
    if (abortOnQualityGate) {
        // Realizar un escaneo de SonarQube
        try {
            def sonarScannerHome = tool name: 'SonarQube Scanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
            def env = Jenkins.instance.getGlobalNodeProperties()[0].getEnvVars()
            def sonarScannerPath = "${sonarScannerHome}/bin/sonar-scanner"
            
            sh """
                ${sonarScannerPath}
            """
        } catch (Exception e) {
            currentBuild.result = 'FAILURE'
            error "Failed to execute SonarQube analysis: ${e.message}"
        }
    } else {
        // Mostrar un mensaje de prueba en lugar de ejecutar SonarQube
        echo "Ejecución de las pruebas de calidad de código (Simulado)"
    }

    // Esperar durante 5 minutos con un timeout
    timeout(time: 5, unit: 'MINUTES') {
        // Puedes agregar aquí las acciones que se deben realizar durante la espera
        // Por ejemplo, ejecutar pruebas unitarias o realizar otras tareas de construcción
    }

    // Verificar el resultado del escaneo de calidad de código
    try {
        // Consultar el estado del Quality Gate en SonarQube
        def qualityGateStatus = sh script: 'sonar-scanner -Dsonar.qualitygate.wait=true', returnStatus: true

        // Si el Quality Gate no pasa, abortar el pipeline
        if (qualityGateStatus != 0) {
            error "Quality Gate no aprobado. Abortando el pipeline."
        }
    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        error "Error al verificar el Quality Gate: ${e.message}"
    }
}

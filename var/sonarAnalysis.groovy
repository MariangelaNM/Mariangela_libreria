// vars/sonarAnalysis.groovy

def call(boolean abortOnFailure = false) {
    try {
        // Ejecutar el escaneo de SonarQube o un echo en su lugar
        sh 'echo "Ejecución de las pruebas de calidad de código"'
        
        // Esperar durante 5 minutos (300 segundos)
        sleep time: 300, unit: 'SECONDS'
        
        // Verificar el resultado del Quality Gate de SonarQube
        // Puedes usar el sonarenv aquí para obtener información sobre el escaneo

        // Si el Quality Gate falla y abortOnFailure es true, abortar el pipeline
        if (abortOnFailure && sonarenv.qualityGate.status == 'ERROR') {
            error('Quality Gate de SonarQube falló. Abortando el pipeline.')
        }
    } catch (Exception e) {
        error("Error en el escaneo de SonarQube: ${e.message}")
    }
}


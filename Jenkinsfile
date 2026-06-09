pipeline {
    agent any

    stages {
        stage('Clonage') {
            steps {
                echo "Repo cloné avec succès !"
                echo "Branche : ${env.GIT_BRANCH}"
            }
        }
        stage('Test Unitaires') {
            steps {
                sh 'mvn -B test'
            }
            post {
                always {
                    junit(
                        testResults: 'target/surefire-reports/*.xml',
                        allowEmptyResults: true
                    )
                }
            }
        }
    }

    post {
        success {
            echo "Tests passés !"
        }
        failure {
            echo "Echec : Vérifier les logs."
        }
    }
}

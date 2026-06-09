pipeline {
    agent any

    environment {
        REGISTRY_USER = 'deepcoding15'
        IMAGE_NAME    = 'ges_stock_api'
        IMAGE_TAG     = "${BUILD_NUMBER}"
        IMAGE_FULL    = "${REGISTRY_USER}/${IMAGE_NAME}:${IMAGE_TAG}"
    }

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

        stage('Build Image Docker') {
            steps {
                script {
                    echo "Construction de ${IMAGE_FULL}..."
                    dockerImage = docker.build("${IMAGE_FULL}")
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline OK - image construite : ${IMAGE_FULL} !"
        }
        failure {
            echo "Echec : Echec au stage : ${env.STAGE_NAME}."
        }
    }
}

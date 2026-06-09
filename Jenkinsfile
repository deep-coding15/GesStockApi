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

        stage('Build & Publication sur Docker Hub') {
            steps {
                script {
                    docker.withRegistry(
                        'https://index.docker.io/v1/', 
                        'docker-hub-credentials') 
                    {
                        def dockerImage = docker.build("${IMAGE_FULL}")
                        dockerImage.push()
                        dockerImage.push('latest')
                    }
                }
            }
        }

        stage('Deploiement') {
            steps {
                sh """
                    docker stop ges_stock_api || true
                    docker rm   ges_stock_api || true
                    docker pull ${IMAGE_FULL}
                    docker run -d --name ges_stock_api --restart unless-stopped -p 8080:8088 ${IMAGE_FULL}
                """
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

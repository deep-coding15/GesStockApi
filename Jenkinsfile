pipeline {
    agent any

    stages {
        stage('Clonage') {
            steps {
                echo "Repo cloné avec succès !"
                echo "Branche : ${env.GIT_BRANCH}"
            }
        }
        stage('Test de Maven') {
            steps {
                bat 'mvn -version'
            }
        }
    }

    post {
        success {
            echo "Premier pipeline Jenkins fonctionnel !"
        }
    }
}

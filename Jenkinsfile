pipeline {
   agent any

   environment {
      HEROKU_API_KEY = credentials('HEROKU_TOKEN_GMAIL')
      DATABASE_URL = credentials('AQUARIO_DB')
   }

   stages {
      stage('Checkout') {
         steps {
           git(url: 'https://github.com/mmuniz75/aquario',
               branch: "${branch}")
         }
      }
      stage('Build image') {
         steps {
            sh 'mvn clean spring-boot:build-image -DskipTests'
         }
      }
      stage('Registry Docker image') {
           steps {
             sh 'docker tag fish-selector:1.0.0 mmuniz/fish-selector:1.0.0'
             sh 'docker push mmuniz/fish-selector:1.0.0'
           }
        }
      stage('Login fly.io') {
         steps {
           sh 'flyctl auth login'
         }
      }
       stage('Push fly.io') {
           steps {
              sh 'flyctl deploy'
           }
        }
   }
}


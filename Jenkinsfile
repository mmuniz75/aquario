pipeline {
   agent any

   environment {
      HEROKU_API_KEY = credentials('HEROKU_API_KEY')
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
            sh 'mvn clean spring-boot:build-image'
         }
      }
      stage('Push Heroku') {
         steps {
           sh 'heroku container:login'
           sh "heroku container:push web -a fish-selector"
         }
      }
       stage('Release Heroku') {
           steps {
              sh 'heroku container:release web --app fish-selector'
           }
        }
   }
}


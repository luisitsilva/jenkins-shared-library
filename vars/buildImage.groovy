#!/usr/bin/env groovy

def call() {
    echo "building the docker image..."
    withCredentials([
            usernamePassword(
                    credentialsId: 'Docker-hub-repo',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
            )
    ]) {

        sh 'docker build -t luititsilva/java-maven-app:5.0 .'

        sh 'docker build -t luititsilva/java-maven-app:6.0 .'


        sh '''
          echo $PASS | docker login -u "$USER" --password-stdin
        '''


        sh 'docker push luititsilva/java-maven-app:5.0'

        sh 'docker push luititsilva/java-maven-app:6.0'

    }
}
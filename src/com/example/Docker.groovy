#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {
    def script

    Docker (script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        echo "building the docker image..."
        withCredentials([
                usernamePassword(
                        credentialsId: 'Docker-hub-repo',
                        usernameVariable: 'USER',
                        passwordVariable: 'PASS'
                )
        ]) {

            sh "docker build -t $imageName ."

            sh '''
          echo $PASS | docker login -u "$USER" --password-stdin
        '''

            sh "docker push $imageName"

        }
    }
}
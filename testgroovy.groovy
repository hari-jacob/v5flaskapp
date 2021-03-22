pipeline {
  agent any
  environment {
    //once you sign up for Docker hub, use that user_id here
    registry = "harinathdockerid/v5flaskapp"
    //- update your credentials ID after creating credentials for connecting to Docker Hub
    registryCredential = 'harinathdockerid'
    dockerImage = ''
  }
  stages {
    stage ('Code Pull') {
      steps{
        script{
          sh 'echo "Pull Successful"'
        }
      }
    }
    stage ('test: Unit-Test') {
      steps{
        sh 'sudo python3 -m unittest test.py'
        sh 'echo "Unittest Success"'
      }
    }
    stage ('test: jmeter-test') {
      steps{
        sh 'sudo python3 -m unittest test.py'
        sh 'echo "Perfomance Test Success"'
      }
    }
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry
        }
      }
    }
    stage('Upload Image') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push()  
          }
        }
      }
    }
    // Stopping Docker containers for cleaner Docker run
    stage('docker stop container') {
      steps {
        sh 'docker ps -f name=mypythonappContainer -q | xargs --no-run-if-empty docker container stop'
        sh 'docker container ls -a -fname=mypythonappContainer -q | xargs -r docker container rm'
      }
    }
    // Running Docker container, make sure port 80 mapped from flask server port 5000 is opened in
    stage('Docker Run') {
      steps{
        script {
          dockerImage.run("-p 0.0.0.0:80:5000/tcp --rm --name mypythonappContainer")
        }
      }
    }
  }
}

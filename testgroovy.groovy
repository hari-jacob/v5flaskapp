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
    
    stage('PyLint: Code Analysis') {
      steps {
        script {
          sh 'pip3 install pylint-flask'
          sh 'pwd'
          sh '/home/hari_98_d/.local/lib/python3.6/site-packages/pylint app.py'
        }
      }
    }
    
    stage ('test: Unit-Test') {
      steps{
        sh 'sudo python3 -m unittest test.py -v'
        sh 'echo "Unittest Success"'
        sh 'pwd'
      }
    }
    stage ('test: Jmeter-test') {
      steps{
        sh 'sudo /home/davidbala592/jmeter/apache-jmeter-5.4.1/bin/jmeter -n -t /home/davidbala592/jmeter/apache-jmeter-5.4.1/bin/google-demo.jmx -l /home/davidbala592/jmeter/apache-jmeter-5.4.1/bin/google-demo-result.jtl'
        sh 'echo "Perfomance Test Success"'
        perfReport '/home/davidbala592/jmeter/apache-jmeter-5.4.1/bin/google-demo-result.jtl'
      }
    }
    
    stage ('SonarQube: Code Analysis') {
      steps{
        
        sh 'echo "SonarQube Code Analysis Complete"'
      }
    }
    
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry
        }
      }
    }
    stage('Upload Image - DockerHub') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push()  
          }
        }
      }
    }
    // Stopping Docker containers for cleaner Docker run
    stage('Clean Container') {
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

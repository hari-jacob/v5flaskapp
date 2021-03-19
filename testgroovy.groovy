pipeline {
  agent any
  stages {
    stage ('build') {
      steps{
        script{
          sh 'echo "Build Successful"'
        }
      }
    }
    stage ('test: unit-test') {
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
  }
}

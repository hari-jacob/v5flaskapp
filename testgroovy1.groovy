pipeline {
  agent any
  stages {
    stage ('build') {
      steps{
        script{
          sh 'echo "Build Successful"'
          sh 'cd /var/lib/jenkins/workspace/unitest_app/'
          sh 'ls'
        }
      }
    }
    stage ('test: unit-test') {
      steps{
        sh 'echo "hello unittest hari07"'
        sh 'echo "A one line step"'
        sh ''' 
        echo "A multiline step"
        echo "ok"
        ls
        '''
      }
    }
  }
}

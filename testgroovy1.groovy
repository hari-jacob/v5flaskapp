pipeline {
  agent any
  stages {
    stage ('build') {
      steps{
        script{
          sh 'echo "Build Successful"'
          def testResult = sh 'pwd'
          echo testResult
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

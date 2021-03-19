pipeline {
  agent any
  stages {
    stage ('build') {
      steps{
        sh 'echo "hello hari07"'
        sh 'echo "A one line step"'
        sh ''' 
        echo "A multiline step"
        pwd
        '''
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

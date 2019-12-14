pipeline {
  environment {
    registry = "shreychaturvedi/simplilearn-devops-project"
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent any
  stages {
    stage('Cloning Git') {
      steps {
        git 'https://github.com/shreychaturvedi-lab/simplilearnproject.git'
      }
    }
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Deploy Image') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
          }
        }
      }
    }
      stage('Build Project') {
        steps{
          script{
            def mvnHome = tool 'maven-3.6.3'
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
         }
       }
    }
    stage('Build Docker Image') {
      steps{
        script{
          // build docker image
      sh "whoami"
      sh "ls -all /var/run/docker.sock"
      sh "mv ./target/devops*.jar ./data" 
      
      sh "docker run -d -p 9000:8090 simplilearn-devops-project:$BUILD_NUMBER"
        }
      }
    }
    stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    }
  }
}
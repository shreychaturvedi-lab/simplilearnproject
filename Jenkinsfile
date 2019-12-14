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
      
      dockerImage = docker.build("simplilearn-devops-project:latest").withRun('-p 9000:8090'){}
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
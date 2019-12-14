node {
    // reference to maven
    // ** NOTE: This 'maven-3.6.3' Maven tool must be configured in the Jenkins Global Configuration.   
    def mvnHome = tool 'maven-3.6.3'

    // holds reference to docker image
    def dockerImage
    // ip address of the docker private repository(nexus)
    
    def dockerRepoUrl = "shreychaturvedi"
    def dockerImageName = "simplilearn-devops-project"
    def dockerImageTag = "${dockerRepoUrl}/${dockerImageName}:${env.BUILD_NUMBER}"
    
    stage('Clone Repo') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/shreychaturvedi-lab/simplilearnproject.git'
      // Get the Maven tool.
      // ** NOTE: This 'maven-3.6.3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'maven-3.6.3'
    }    
  
    stage('Build Project') {
      // build project via maven
      sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
    }
	
	
		
    stage('Build Docker Image') {
      // build docker image
      sh "whoami"
      sh "ls -all /var/run/docker.sock"
      sh "mv ./target/devops*.jar ./data" 
      
      dockerImage = docker.build("simplilearn-devops-project:latest").withRun('-p 9000:8090'){}
    }
   
    stage('Deploy Docker Image'){
      
      // deploy docker image to nexus

      echo "Docker Image Tag Name: ${dockerImageTag}"

      sh "docker login -u shreychaturvedi -p Space007\$"
      sh "docker tag ${dockerImageName} ${dockerImageTag}"
      sh "docker push ${dockerImageTag}"
    }
}

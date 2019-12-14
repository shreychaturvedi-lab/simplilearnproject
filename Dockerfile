FROM openjdk:12-jdk
ADD ./data/devops-0.0.1-SNAPSHOT.jar devops-0.0.1-SNAPSHOT.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","devops-0.0.1-SNAPSHOT.jar"]


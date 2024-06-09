FROM openjdk:21-jdk-slim-buster

WORKDIR /home/app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
CMD ["java","-jar","app.jar"]


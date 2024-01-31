FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar
COPY ./env.properties /app/env.properties


ENTRYPOINT ["java", "-jar", "/app.jar"]
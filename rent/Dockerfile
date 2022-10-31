FROM openjdk:17-jdk-slim
MAINTAINER com.mateusbalda
ARG JAR_FILE=rent/target/*.jar
COPY ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker-demo", "/rent.jar"]
FROM openjdk:17-alpine
MAINTAINER com.mateusbalda
VOLUME /tmp
EXPOSE 8087

COPY target/discovery*.jar /discovery.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker-demo", "/discovery   .jar"]
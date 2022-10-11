FROM openjdk:17-alpine
MAINTAINER com.mateusbalda
VOLUME /tmp
EXPOSE 8087

COPY target/rent*.jar /rent.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker-demo", "/rent.jar"]
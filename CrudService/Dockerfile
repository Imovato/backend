FROM openjdk:17-alpine
MAINTAINER com.mateusbalda
VOLUME /tmp
EXPOSE 8081

COPY target/imobiliaria*.jar /imobiliaria.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker-demo", "/imobiliaria.jar"]
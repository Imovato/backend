#FROM openjdk:17-alpine
#MAINTAINER com.mateusbalda
#VOLUME /tmp
#EXPOSE 8081
#
#COPY CrudService/target/*.jar /crud-service.jar
#
#ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker-demo", "/crud-service.jar"]

## In this way the file, creates the image along with maven
FROM maven:3.6-openjdk-17-slim AS MAVEN_BUILD
COPY CrudService/pom.xml /build/
COPY CrudService/src /build/src/
WORKDIR /build/
RUN mvn package -DskipTests

FROM openjdk:17-alpine
MAINTAINER com.mateusbalda
EXPOSE 8081
COPY --from=MAVEN_BUILD /build/target/*.jar imobiliaria.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker-demo","/imobiliaria.jar"]

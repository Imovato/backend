FROM maven:3.6-openjdk-17-slim AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package -DskipTests

FROM openjdk:17-alpine
MAINTAINER com.mateusbalda
EXPOSE 8081
COPY --from=MAVEN_BUILD /build/target/*.jar imobiliaria.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker-demo","/imobiliaria.jar"]
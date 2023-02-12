FROM maven:3.6-openjdk-17-slim AS MAVEN_BUILD
COPY rent/pom.xml /build/
COPY rent/src /build/src/
WORKDIR /build/
RUN mvn package -DskipTests

FROM openjdk:17-alpine
MAINTAINER com.mateusbalda
EXPOSE 8085
COPY --from=MAVEN_BUILD /build/target/*.jar rent.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker-demo","/rent.jar"]
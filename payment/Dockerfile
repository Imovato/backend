FROM openjdk:19-slim
MAINTAINER com.jmerlugof
VOLUME /tmp
EXPOSE 8088

COPY target/payment*.jar /payment.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker-demo", "/payment.jar"]
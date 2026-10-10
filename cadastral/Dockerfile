# ---------- stage 1: build ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar apenas o pom para aproveitar o cache do Maven
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Copiar código restante
COPY . .

# Gerar o JAR (sem testes para agilizar)
RUN mvn -q clean package -DskipTests


# ---------- stage 2: runtime ----------
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Copia o JAR final
COPY --from=build /app/target/*.jar app.jar

# ECS supplies PORT at runtime; 8080 remains the generic container default.
EXPOSE 8080

# Importante: garantir que a aplicação leia PORT corretamente
ENV SERVER_PORT=${PORT}

CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]

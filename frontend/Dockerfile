# Estágio de build
FROM ubuntu:latest AS build
RUN apt-get update && apt-get install -y openjdk-21-jdk maven
WORKDIR /app
COPY . .
# Entra na pasta do backend antes de construir
WORKDIR /app/backend
RUN mvn clean install -DskipTests

# Estágio de produção
FROM openjdk:21-jdk
WORKDIR /app
EXPOSE 8080
# Copia o JAR do local correto
COPY --from=build /app/backend/target/gestao-moradias-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
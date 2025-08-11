FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install -y openjdk-21-jdk maven
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:21-jdk
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/gestao-moradias-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]

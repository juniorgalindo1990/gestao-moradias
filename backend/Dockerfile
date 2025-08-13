FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY backend/pom.xml backend/pom.xml
RUN mvn -f backend/pom.xml -q -DskipTests verify || true

COPY backend/ backend/
RUN mvn -f backend/pom.xml -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/backend/target/*SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app/app.jar"]

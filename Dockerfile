# FROM openjdk:21-jdk
FROM maven:3.9.11-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY . .

RUN mvn clean install -DskipTest

FROM eclipse-temurin:21-jdk-alpine


WORKDIR /app

COPY --from=build /app/target/*.jar /app/bff-agendador.jar

EXPOSE 8083

CMD ["java", "-jar", "/app/bff-agendador.jar"]
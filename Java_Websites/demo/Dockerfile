# Build stage: a projekt lefordítása és JAR csomagolása Maven segítségével.
# Java verzió a pom.xml-ben van beállítva (Java 17).
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage: ez a konténer fogja futtatni az alkalmazást.
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app

#jar fájl adtunk meg a start.spring.io oldalon. A build szó a fentebb létrehozott stage-ra utal.
# A JAR fájl a build stage-ből kerül ide.
COPY --from=build /app/target/*.jar demo.jar

# A Spring Boot alkalmazás a 8080-as porton fut.
EXPOSE 8080 

# ENTRYPOINT: a konténer indításakor a JAR futtatása.
ENTRYPOINT ["java","-jar","demo.jar"]
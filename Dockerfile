FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package  -DskipTests

FROM eclipse-temurin:17-jdk


WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar


VOLUME /myProjectSpring
# pour suavgarder les fichiers l'application

EXPOSE 8080
#port du conteneur pas local

CMD [ "java","-jar","app.jar" ]
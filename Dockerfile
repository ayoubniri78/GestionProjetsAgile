FROM eclipse-temurin:17-jdk


WORKDIR /app

COPY target/*.jar app.jar

VOLUME /myProjectSpring
# pour suavgarder les fichiers l'application

EXPOSE 8080
#port du conteneur pas local

CMD [ "java","-jar","app.jar" ]
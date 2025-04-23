FROM openjdk:21-jdk
ARG JAR_FILE=build/libs/*.jar
COPY backend-0.0.1-SNAPSHOT.jar app.jar
COPY .env .env
ENTRYPOINT ["java", "-jar", "/app.jar"]

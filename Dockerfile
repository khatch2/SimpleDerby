FROM openjdk:17-alpine
LABEL authors="KhatchShah"
ARG JAR_FILE=target/SimpleDerby-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
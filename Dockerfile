FROM eclipse-temurin:17-jdk-alpine
LABEL authors="someb"
VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
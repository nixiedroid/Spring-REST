FROM eclipse-temurin:17-jdk-alpine
LABEL authors="someb"
COPY build/libs/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/app.jar"]
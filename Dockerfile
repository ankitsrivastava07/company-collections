FROM amazoncorretto:21-alpine
EXPOSE 8080
COPY target/*.jar app.jar
CMD ["java", "-jar", "/app.jar"]

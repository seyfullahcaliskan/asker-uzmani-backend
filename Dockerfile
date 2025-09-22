FROM eclipse-temurin:24-jdk
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8008
ENTRYPOINT ["java", "-jar", "app.jar"]

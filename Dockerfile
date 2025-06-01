FROM eclipse-temurin:17-jre-alpine
COPY target/*.jar usm.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usm.jar", "--spring.profiles.active=prod"]
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/topic-reactions-service-1.0.0.jar topic-reactions-service-1.0.0.jar
EXPOSE 7373
ENTRYPOINT ["java", "-jar", "topic-reactions-service-1.0.0.jar"]
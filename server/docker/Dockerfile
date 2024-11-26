FROM openjdk:23-ea-5-jdk-slim

WORKDIR /app/server

COPY /server/target/security-0.0.1-SNAPSHOT.jar /app/server/app.jar

CMD ["java", "-jar", "app.jar"]
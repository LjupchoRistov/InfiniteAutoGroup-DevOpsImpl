FROM openjdk:21-jdk-slim

WORKDIR /app

COPY ./target/infinite-auto-group-app-0.0.1-SNAPSHOT.jar /app/infinite-auto-group-app-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/infinite-auto-group-app-0.0.1-SNAPSHOT.jar"]

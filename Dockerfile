FROM openjdk:17-alpine

WORKDIR /spring-learning

COPY . .

ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]

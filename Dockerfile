FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

COPY .mvn .mvn
COPY mvnw ./
COPY pom.xml ./
COPY src/ src

# mvn -N io.takari:maven:wrapper - update mvnw
RUN ./mvnw clean package spring-boot:repackage

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/Kafka_producer.jar /app/app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
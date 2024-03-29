FROM openjdk:17-jdk-alpine

RUN adduser --system spring-boot && addgroup --system spring-boot && adduser spring-boot spring-boot
USER spring-boot

WORKDIR /app

COPY build/libs/cloudStorage-0.0.1-SNAPSHOT-plain.jar ./application.jar
COPY build/dependency ./libs

ENTRYPOINT ["java", "-cp", "libs/*:application.jar", "com.amida.cloudStorage.CloudStorageApplication"]


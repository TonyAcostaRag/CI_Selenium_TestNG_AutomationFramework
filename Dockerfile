FROM maven:3.9.12-eclipse-temurin-17

WORKDIR /workspace

# COPY pom.xml .

COPY src ./src

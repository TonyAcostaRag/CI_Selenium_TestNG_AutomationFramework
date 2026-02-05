FROM maven:3.9.12-eclipse-temurin-11-alpine

RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    chromium \
    chromium-driver \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /src

# Cache dependencies
COPY pom.xml .
RUN mvn -q -B dependency:resolve

# Copy project
COPY src ./src

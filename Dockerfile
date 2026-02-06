FROM selenium/standalone-all-browsers:latest

USER root

# Install Maven
RUN apt-get update && apt-get install -y maven \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /src

# Cache dependencies
COPY pom.xml .
RUN mvn -q -B dependency:resolve

# Copy project
COPY src ./src

FROM selenium/standalone-chrome-firefox:latest

WORKDIR /app

# Cache dependencies
COPY pom.xml .
RUN mvn -q -B dependency:resolve

# Copy project
COPY src ./src

# Default execution
CMD ["mvn", "clean", "test"]

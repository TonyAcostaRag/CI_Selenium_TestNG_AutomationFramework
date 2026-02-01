FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /tests

# Cache dependencies
COPY pom.xml .
RUN mvn -q -B dependency:resolve

# Copy project
COPY src ./src
COPY testng.xml .

# Default execution
CMD ["mvn", "clean", "test", "-DsuiteXmlFile=testng.xml"]

# Use the base image with Java 8 and Maven installed
FROM maven:3.8.4-openjdk-8-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the container
COPY pom.xml .
COPY src ./src

# Build the Maven project
RUN mvn clean package -DskipTests

# Create a new stage for the production image
FROM openjdk:8-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/focal-test-automation-1.0-SNAPSHOT.jar .

# Copy the HTML reports and screenshots to the container
COPY html-reports /app/html-reports

# Specify the command to run the tests
CMD ["mvn", "test"]

# Expose a volume for the HTML reports and screenshots
VOLUME /app/html-reports

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "focal-test-automation-1.0-SNAPSHOT.jar"]
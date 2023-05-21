FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:resolve

# Copy the source code to the container
COPY src ./src

# Build the application
RUN mvn package

# Specify the command to run the application
CMD ["java", "-jar", "target/*.jar"]

# Expose the required port
EXPOSE 8080

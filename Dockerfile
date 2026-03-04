# Use a valid OpenJDK 17 image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper & pom first (to cache dependencies)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give mvnw execute permission
RUN chmod +x mvnw

# Build the Spring Boot jar (skip tests for faster build)
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Copy the jar to a known name
RUN cp target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","/app/app.jar"]
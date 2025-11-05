# Multi-stage build for Spring Boot application with Node.js/SASS compilation

# Stage 1: Build SASS/CSS assets
FROM node:20-alpine AS css-builder
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY src/main/resources/static/scss ./src/main/resources/static/scss
RUN npm run build:css

# Stage 2: Build Spring Boot application
FROM gradle:8.5-jdk21 AS java-builder
WORKDIR /app
# Copy gradle configuration
COPY build.gradle ./
# Copy source code
COPY src ./src
# Copy compiled CSS from previous stage
COPY --from=css-builder /app/src/main/resources/static/css ./src/main/resources/static/css
# Build the application
RUN gradle clean build -x test --no-daemon

# Stage 3: Runtime
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copy the built JAR from the builder stage
COPY --from=java-builder /app/build/libs/*.jar app.jar
# Expose the application port
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

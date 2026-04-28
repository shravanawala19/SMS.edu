# ─── Stage 1: Build the WAR ──────────────────────────────────────────────────
FROM maven:3.8.8-eclipse-temurin-11 AS build
WORKDIR /app

# Cache dependencies first (only re-runs when pom.xml changes)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests -B

# ─── Stage 2: Deploy to Tomcat ───────────────────────────────────────────────
FROM tomcat:9.0-jdk11-openjdk-slim

# Remove the default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy our WAR as ROOT.war so the app is served at "/"
COPY --from=build /app/target/StudentManagementSystem.war \
     /usr/local/tomcat/webapps/ROOT.war

# Railway / cloud platforms inject the PORT env var
ENV PORT=8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]

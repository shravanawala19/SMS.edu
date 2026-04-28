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
FROM tomcat:9.0-jdk11-temurin

# Remove the default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy our WAR as ROOT.war so the app is served at "/"
COPY --from=build /app/target/StudentManagementSystem.war \
     /usr/local/tomcat/webapps/ROOT.war

# Railway injects PORT env var at runtime; default to 8080
ENV PORT=8080
EXPOSE 8080

# Write a startup script that patches Tomcat's port at runtime then launches it
RUN printf '#!/bin/sh\n\
PORT="${PORT:-8080}"\n\
echo "Starting Tomcat on port $PORT"\n\
sed -i "s/port=\\"8080\\"/port=\\"${PORT}\\"/" /usr/local/tomcat/conf/server.xml\n\
exec catalina.sh run\n' > /start.sh && chmod +x /start.sh

CMD ["/start.sh"]

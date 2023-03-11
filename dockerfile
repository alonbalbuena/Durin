# Multi stage build
# First step we build the image of the spring app with maven
FROM maven:3.8.6-eclipse-temurin-18 as build
COPY pom.xml /app/
COPY src /app/src
#RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package -DskipTests
RUN mvn -f /app/pom.xml clean package -DskipTests
# Second step we convert the spring app in a java executable
FROM eclipse-temurin:18
RUN mkdir /opt/app
COPY --from=build /app/target/Durin-1.0.jar /opt/app
ENTRYPOINT ["java", "-jar", "/opt/app/Durin-1.0.jar","--debug"]
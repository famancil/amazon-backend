# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="mancilla.fernando@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} DEMO.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/DEMO.jar"]

# Set TimeZone
RUN apk add --no-cache tzdata
ENV TZ America/Santiago
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
FROM openjdk:11

WORKDIR /opt/server
COPY ./target/booking-service-0.0.1-SNAPSHOT.jar server.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "server.jar"]

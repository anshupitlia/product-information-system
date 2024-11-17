FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/secure-connect.zip src/main/resources/secure-connect.zip
COPY src/main/resources/client.truststore.jks src/main/resources/client.truststore.jks
ENTRYPOINT ["java","-jar","/app.jar"]

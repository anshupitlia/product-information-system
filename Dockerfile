FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/secure-connect.zip src/main/resources/secure-connect.zip
ENTRYPOINT ["java","-jar","/app.jar"]

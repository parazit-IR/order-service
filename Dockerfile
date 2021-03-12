FROM openjdk:11-jdk-alpine
VOLUME /tmp
ADD target/*.jar app.jar
ENTRYPOINT exec java -jar /app.jar order-service

# mvn clean install
# docker build -t order-service .
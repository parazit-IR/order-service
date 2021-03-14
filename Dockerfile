FROM adoptopenjdk/openjdk11
VOLUME /tmp
EXPOSE 8080
ADD target/*.jar app.jar
ENTRYPOINT exec java -jar /app.jar order-service

# mvn clean install
# docker build -t order-service .

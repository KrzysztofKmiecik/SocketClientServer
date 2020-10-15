FROM openjdk:8-jdk-alpine3.9
ADD target/DataSocket-1.0-SNAPSHOT.jar .
EXPOSE 4444

CMD java -jar DataSocket-1.0-SNAPSHOT.jar
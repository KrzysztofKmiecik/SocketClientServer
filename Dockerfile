FROM openjdk:8-jdk-alpine3.9
ADD out/artifacts/DataSocket_jar/DataSocket.jar .
EXPOSE 4444
RUN echo "Europe/Warsaw" > /etc/timezone

CMD java -jar DataSocket.jar
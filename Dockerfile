FROM openjdk:8-jdk-alpine3.9
ADD out/artifacts/SocketClientServer_jar/SocketClientServer.jar .
COPY src/main/resources/static/ipconfig.csv .
COPY src/main/resources/static/prefixes.csv .
EXPOSE 4444
RUN echo "Europe/Warsaw" > /etc/timezone

CMD java -jar SocketClientServer.jar  ipconfig.csv prefixes.csv
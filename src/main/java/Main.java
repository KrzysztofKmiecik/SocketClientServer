import client.IPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.IPServerClient;
import server.ServerClient;


public class Main {

    public static void main(String[] args) {
        JavaServer();
    }

    private static void JavaServer() {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Hello I'm JavaServer waiting for connection");
        final String[] prefixes = {"5S", "6S", "7S", "5T", "6T", "7T"};
        final ServerClient javaServer = IPServerClient.with(4444,
                new IPClient("10.235.241.235", 24405),
                prefixes);
        javaServer.connectLoop();
    }

}



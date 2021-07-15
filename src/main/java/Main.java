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
        logger.info("Hello I'm JavaServer(v90) waiting for connection ");

        final String[] prefixes = {"5S", "6S", "7S", "5T", "6T", "7T", "7U", "9U", "2V"};
        final ServerClient javaServer = IPServerClient.with(4444,
                new IPClient("10.235.241.235", 24405),
                prefixes);


       /* CSVFileService myCsvPrefix = new CSVFileUseCase(prefixFile);
        List<String> prefixes = myCsvPrefix.readPrefixes();
        CSVFileService myCsvConfig = new CSVFileUseCase(configFile);
        ConfigCsvFile myConfigCsvFile = myCsvConfig.readConfig().get(0);
        final ServerClient javaServer = IPServerClient.with(myConfigCsvFile.getServerPort(),
                new IPClient(myConfigCsvFile.getClientIp(), myConfigCsvFile.getClientPort()),
                prefixes.toArray(new String[0]));*/
        javaServer.connectLoop();
    }

}



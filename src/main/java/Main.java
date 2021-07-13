import CsvFile.CSVFileService;
import CsvFile.CSVFileUseCase;
import CsvFile.ConfigCsvFile;
import client.IPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.IPServerClient;
import server.ServerClient;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        JavaServer();
    }

    private static void JavaServer() {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Hello I'm JavaServer waiting for connection");
        //   final String[] prefixes = {"5S", "6S", "7S", "5T", "6T", "7T","7U","9U"};
        CSVFileService myCsvPrefix = new CSVFileUseCase("src/main/resources/static/prefixes.csv");
        List<String> prefixes = myCsvPrefix.readPrefixes();
        CSVFileService myCsvConfig = new CSVFileUseCase("src/main/resources/static/ipconfig.csv");
        ConfigCsvFile myConfigCsvFile = myCsvConfig.readConfig().get(0);
        final ServerClient javaServer = IPServerClient.with(myConfigCsvFile.getServerPort(),
                new IPClient(myConfigCsvFile.getClientIp(), myConfigCsvFile.getClientPort()),
                prefixes.toArray(new String[0]));
        javaServer.connectLoop();
    }

}



import CsvFile.CSVFileService;
import CsvFile.CSVFileUseCase;
import CsvFile.model.ConfigCsvFile;
import client.IPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.IPServerClient;
import server.ServerClient;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        JavaServer(args[0],args[1]);
    }

    private static void JavaServer(final String configFile,final String prefixFile) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Hello I'm JavaServer(v90) waiting for connection ");

    /*    final String[] prefixes = {"5S", "6S", "7S", "5T", "6T", "7T", "7U", "9U", "2V"};
        final ServerClient javaServer = IPServerClient.with(4444,
                new IPClient("10.235.241.235", 24405),
                prefixes);*/


       CSVFileService myCsvPrefix = new CSVFileUseCase(prefixFile);
        List<String> prefixes = myCsvPrefix.readPrefixes();
        CSVFileService myCsvConfig = new CSVFileUseCase(configFile);
        ConfigCsvFile myConfigCsvFile = myCsvConfig.readConfig().get(0);
        final ServerClient javaServer = IPServerClient.with(myConfigCsvFile.getServerPort(),
                new IPClient(myConfigCsvFile.getClientIp(), myConfigCsvFile.getClientPort()),
                prefixes.toArray(new String[0]));
        javaServer.connectLoop();
    }

}



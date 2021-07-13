package CsvFile;

import java.util.List;

public interface CSVFileService {
    List<String> readPrefixes();
    List<ConfigCsvFile> readConfig();
}

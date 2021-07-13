package CsvFile;

import CsvFile.model.ConfigCsvFile;

import java.util.List;

public interface CSVFileService {
    List<String> readPrefixes();
    List<ConfigCsvFile> readConfig();
}

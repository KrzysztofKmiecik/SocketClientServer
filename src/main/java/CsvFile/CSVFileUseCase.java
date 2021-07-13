package CsvFile;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CSVFileUseCase implements CSVFileService {
    private String filename;

    public CSVFileUseCase(String filename) {
        this.filename = filename;
    }

    @Override
    public List<String> readPrefixes() {
        List<PcbPrefix> prefixes = Collections.emptyList();
        try {
            prefixes = new CsvToBeanBuilder(new FileReader(this.filename))
                    .withType(PcbPrefix.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return prefixes.stream().map(p -> p.getPrefix()).collect(Collectors.toList());
    }

    @Override
    public List<ConfigCsvFile> readConfig() {
        List<ConfigCsvFile> myConfigCsvFile = Collections.EMPTY_LIST;
        try {
            myConfigCsvFile = new CsvToBeanBuilder(new FileReader(this.filename))
                    .withType(ConfigCsvFile.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return myConfigCsvFile;
    }
}

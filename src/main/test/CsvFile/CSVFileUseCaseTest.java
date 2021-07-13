package CsvFile;

import CsvFile.model.ConfigCsvFile;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CSVFileUseCaseTest {

    @Test
    public void readPrefixes() {
        //given
        CSVFileService myCsv = new CSVFileUseCase("src/main/resources/static/prefixes.csv");
        List<String> expected = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T", "7U", "9U");
        //when
        List<String> actual = myCsv.readPrefixes();
        //   pcbPrefixes.stream().forEach(System.out::println);
        //then
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void readConfig() {
        //given
        CSVFileService myConfigCsv = new CSVFileUseCase("src/main/resources/static/ipconfig.csv");
        ConfigCsvFile expected = new ConfigCsvFile(4444, "10.235.241.235", 24405);
        //when
        ConfigCsvFile actualConfig = myConfigCsv.readConfig().get(0);
        //   pcbPrefixes.stream().forEach(System.out::println);
        //then
        Assert.assertEquals(expected, actualConfig);

    }
}
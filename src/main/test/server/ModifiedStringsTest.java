package server;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ModifiedStringsTest {

    @Test
    public void getMyModifiedStringWithPrefixFOUNDwith1() {

        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        String expected = "BCNF|id=5SG1KF4|id=test|status=PASS|map=11";
        String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes, "BREQ|process=MILLING|station=SMT_MILLING_1|id=5SG1KF4", "BCNF|id=5SG1KF4|status=PASS|map=11");
        Assert.assertEquals(expected, current);
    }

    @Test
    public void getMyModifiedStringWithPrefixFOUNDwith0() {

        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        String expected = "BCNF|id=5SG1KF4|id=test|status=PASS|map=00";
        String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes, "BREQ|process=MILLING|station=SMT_MILLING_1|id=5SG1KF4", "BCNF|id=5SG1KF4|status=PASS|map=0");
        Assert.assertEquals(expected, current);
    }

    @Test
    public void getMyModifiedStringWithPrefixFOUNDwith0FAIL() {

        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        String expected = "BCNF|id=5SG1KF4|id=test|status=FAIL|map=00";
        String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes, "BREQ|process=MILLING|station=SMT_MILLING_1|id=5SG1KF4", "BCNF|id=5SG1KF4|status=FAIL|map=0");
        Assert.assertEquals(expected, current);
    }

    @Test
    public void getMyModifiedStringWithPrefixFOUNDwith0FAIL_emptyID() {

        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        String expected = "BCNF|id=|status=FAIL|map=0";
        String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes, "BREQ|process=MILLING|station=SMT_MILLING_1|id=", "BCNF|id=|status=FAIL|map=0");
        Assert.assertEquals(expected, current);
    }

    @Test
    public void getMyModifiedStringWithPrefixNOTFOUND() {
        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        String expected = "BCNF|id=9FG1ID6X|status=PASS|map=1";
        String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes, "BREQ|process=MILLING|station=SMT_MILLING_1|id=9FG1ID6X", "BCNF|id=9FG1ID6X|status=PASS|map=1");
        Assert.assertEquals(expected, current);

    }




    @Test
    public void getValue() {

        String FISresponse = "BCNF|id=9FG1ID6X|status=PASS|map=1";
        List<String> FISresponseList = ModifiedStrings.convertFISResponseToList(FISresponse);
        String expectedValue = "9FG1ID6X";
        String id = ModifiedStrings.getValue(FISresponseList, "id");
        Assert.assertEquals(expectedValue, id);
    }
}
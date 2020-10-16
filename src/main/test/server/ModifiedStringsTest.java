package server;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ModifiedStringsTest {

    @Test
    public void getMyModifiedStringWithPrefixNOTFOUND() {

        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        String expected = "BCNF|id=5SG1KF4|id=test|status=PASS|map=11";
        String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes, "BREQ|process=MILLING|station=SMT_MILLING_1|id=5SG1KF4", "BCNF|id=5SG1KF4|status=PASS|map=1");
        Assert.assertEquals(expected, current);
    }



    @Test
    public void getMyModifiedStringWithPrefixFOUND() {
        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        String expected = "BCNF|id=9FG1ID6X|status=PASS|map=1";
        String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes, "BREQ|process=MILLING|station=SMT_MILLING_1|id=9FG1ID6X", "BCNF|id=9FG1ID6X|status=PASS|map=1");
        Assert.assertEquals(expected, current);

    }





    @Test
    public void getMyModifiedString() {
    }

    @Test
    public void listToStringWithSeparator() {
    }
}
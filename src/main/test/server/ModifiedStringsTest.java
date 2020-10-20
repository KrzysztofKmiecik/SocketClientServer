package server;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ModifiedStringsTest {

    @Test
    public void getMyModifiedStringWithPrefixFOUNDwith1() {
        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        final String expected = "BCNF|id=5SG1KF4|id=test|status=PASS|map=10";
        final String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes,
                   "BCNF|id=5SG1KF4|status=PASS|map=1");
        Assert.assertEquals(expected, current);
    }

    @Test
    public void getMyModifiedStringWithPrefixFOUNDwith0() {
        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        final String expected = "BCNF|id=5SG1KF4|id=test|status=PASS|map=00";
        final String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes,
                   "BCNF|id=5SG1KF4|status=PASS|map=0");
        Assert.assertEquals(expected, current);
    }

    @Test
    public void getMyModifiedStringWithPrefixFOUNDwith0FAIL() {
        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        final String expected = "BCNF|id=5SG1KF4|id=test|status=FAIL|map=00";
        final String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes,
                   "BCNF|id=5SG1KF4|status=FAIL|map=0");
        Assert.assertEquals(expected, current);
    }

    @Test
    public void getMyModifiedStringWithPrefixFOUNDwith0FAIL_emptyID() {
        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        final String expected = "BCNF|id=|status=FAIL|map=0";
        final String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes,
                   "BCNF|id=|status=FAIL|map=0");
        Assert.assertEquals(expected, current);
    }

    @Test
    public void getMyModifiedStringNOmap() {
        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        final String expected = "BACK|id=5SG1KF4|status=PASS";
        final String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes,
                   "BACK|id=5SG1KF4|status=PASS");
        Assert.assertEquals(expected, current);
    }

    @Test
    public void getMyModifiedStringWithPrefixNOTFOUND() {
        final List<String> prefixes = Arrays.asList("5S", "6S", "7S", "5T", "6T", "7T");
        final String expected = "BCNF|id=9FG1ID6X|status=PASS|map=1";
        final String current = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes,
                   "BCNF|id=9FG1ID6X|status=PASS|map=1");
        Assert.assertEquals(expected, current);

    }

    @Test
    public void getValue() {
        final String FISresponse = "BCNF|id=9FG1ID6X|status=PASS|map=1";
        final List<String> FISresponseList = ModifiedStrings.convertFISResponseToList(FISresponse);
        final String expectedValue = "9FG1ID6X";
        final String id = ModifiedStrings.getValue(FISresponseList, "id");
        Assert.assertEquals(expectedValue, id);
    }


    @Test
    public void getValueNulll() {
        final String FISresponse = null;
        final List<String> FISresponseList = ModifiedStrings.convertFISResponseToList(FISresponse);
        final String expectedValue = "";
        final String id = ModifiedStrings.getValue(FISresponseList, null);
        Assert.assertEquals(expectedValue, id);
    }

    @Test
    public void getValueWrongFISFormat() {
        final String FISresponse = "asdfdfasdfasdfa";
        final List<String> FISresponseList = ModifiedStrings.convertFISResponseToList(FISresponse);
        final String expectedValue = "";
        final String id = ModifiedStrings.getValue(FISresponseList, null);
        Assert.assertEquals(expectedValue, id);
    }

    @Test
    public void testListToStringWithSeparator() {
        final List<String> stringList = Arrays.asList("BCNF", "id=5SG1KF4", "id=test" , "status=FAIL", "map=00");
        final String expected="BCNF|id=5SG1KF4|id=test|status=FAIL|map=00";
        final String actual=ModifiedStrings.listToStringWithSeparator(stringList,"|");
        Assert.assertEquals(expected,actual);
    }
}
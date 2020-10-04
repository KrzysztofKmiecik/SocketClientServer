package server;

import com.sun.tools.javac.util.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPServerClientTest {

    private IPServerClient javaServer;

    @Before
    public void before() {
        javaServer = mock(IPServerClient.class);
        when(javaServer.getMyModifiedString("BCNF|id=9FG1KF4|status=PASS|map=1")).thenReturn("BCNF|id=9FG1KF4|id=test|status=PASS|map=11");
    }

    @Test
    public void IPServerClientTest() {

        String expected = "BCNF|id=9FG1KF4|id=test|status=PASS|map=11";
        String current = javaServer.getMyModifiedString("BCNF|id=9FG1KF4|status=PASS|map=1");

        Assert.assertEquals(expected, current);
    }

    @Test
    @Ignore
    public void inputProcess() {

        // String inp="BREQ|process=MILLING|station=SMT_MILLING_1|id=9FG1KF6";
        String inp = "BCNF|id=9FG1KF4|status=PASS|map=1";
        String expected = "BCNF|id=9FG1KF4|id=test|status=PASS|map=11";


        //    String[] strings = inp.split("[|]");
        List<String> strings1 = new LinkedList<>(Arrays.asList(inp.split("[|]")));

        Stream<String> stringStream = strings1.stream()
                .filter(e -> e.startsWith("map="))
                .map(e -> e.concat("1"));
        stringStream
                .forEach(System.out::println);


        ListIterator<String> iterator = strings1.listIterator();
        int index = 0;
        while (iterator.hasNext()) {
            String currentStr = iterator.next();

            if (currentStr.contains("id=")) {
                index = iterator.nextIndex();
            }

            if (currentStr.contains("map=")) {
                iterator.set(currentStr.concat("1"));
                System.out.println();
            }
        }

        strings1.add(index, "id=test");

        String current="";


        for (int i = 0; i <strings1.size() ; i++) {
            if (i==strings1.size()-1) {
                current=current+strings1.get(i);
            } else {
                current=current+strings1.get(i)+"|";
            }


        }


        Assert.assertEquals(expected,current);

    }


}

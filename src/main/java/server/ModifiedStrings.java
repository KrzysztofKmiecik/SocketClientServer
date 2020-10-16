package server;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ModifiedStrings {

    public static  String getMyModifiedStringWithPrefix(List<String> prefixes, String receivedFromClient, String receivedFromFis) {
        String myModifiedString;
        for (String prefix : prefixes) {
            if (receivedFromClient.contains("id="+prefix)) {
                myModifiedString = getMyModifiedString(receivedFromFis);
                return myModifiedString;
            }
        }
        return receivedFromFis;
    }


    public static  String getMyModifiedString(final String receivedFromFis) {

        List<String> stringList = new LinkedList<>(Arrays.asList(receivedFromFis.split("[|]")));

        ListIterator<String> iterator = stringList.listIterator();
        int index = 0;
        while (iterator.hasNext()) {
            String currentStr = iterator.next();
            if (currentStr.contains("id=")) {
                index = iterator.nextIndex();
            }
            if (currentStr.contains("map=")) {
                iterator.set(currentStr.concat("1"));
            }
        }

        stringList.add(index, "id=test");

        String current = "";
        current = listToStringWithSeparator(stringList, "|");
        return current;
    }


    public static String listToStringWithSeparator(final List<String> stringList, final String separator) {

        String returnStr = "";
        for (int i = 0; i < stringList.size(); i++) {
            if (i == stringList.size() - 1) {
                returnStr = returnStr + stringList.get(i);
            } else {
                returnStr = returnStr + stringList.get(i) + separator;
            }
        }
        return returnStr;
    }

}

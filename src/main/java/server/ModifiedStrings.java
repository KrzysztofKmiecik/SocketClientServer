package server;

import java.util.*;

public class ModifiedStrings {

    public static String getMyModifiedStringWithPrefix(List<String> prefixes, String receivedFromFis) {
        String myModifiedString;
        if (receivedFromFis.contains("BCNF")){
            for (String prefix : prefixes) {
                if (receivedFromFis.contains("id=" + prefix)) {
                    myModifiedString = listToStringWithSeparator(prepareFinalList(receivedFromFis), "|");
                    return myModifiedString;
                }
            }

        }

        return receivedFromFis;
    }

    public static List<String> prepareFinalList(String receivedFromFis) {
        List<String> stringList = addIDTest(receivedFromFis);
        List<String> finalList = addMap(stringList);
        return finalList;
    }

    public static List<String> addIDTest(final String receivedFromFis) {

        List<String> stringList = convertFISResponseToList(receivedFromFis);

        int index = getIndex(stringList, "id");

        stringList.add(index + 1, "id=test");


        return stringList;
    }

    public static List<String> addMap(final List<String> receivedFromFisList) {

        List<String> finalList = new ArrayList<>(receivedFromFisList);

        int mapIndex = getIndex(receivedFromFisList, "map");
        if (mapIndex >= 0) {
            String mapValue = getValue(receivedFromFisList, "map");
            StringBuilder newMapValue = new StringBuilder(mapValue);
            newMapValue.append("0");
            newMapValue.insert(0, "map=");
            String finalString = newMapValue.toString();
            finalList.set(mapIndex, finalString);

        }

        return finalList;
    }

    public static List<String> addMapWithCopy(final List<String> receivedFromFisList) {

        List<String> finalList = new ArrayList<>(receivedFromFisList);

        int mapIndex = getIndex(receivedFromFisList, "map");
        if (mapIndex >= 0) {
            String mapValue = getValue(receivedFromFisList, "map");
            StringBuilder newMapValue = new StringBuilder(mapValue);
            if ("0".equals(mapValue)) {
                newMapValue.append("0");
            } else if ("1".equals(mapValue)) {
                newMapValue.append("1");

            } else {
                newMapValue.append("0");
            }

            newMapValue.insert(0, "map=");
            String finalString = newMapValue.toString();
            finalList.set(mapIndex, finalString);

        }

        return finalList;
    }

    public static List<String> convertFISResponseToList(final String receivedFromFis) {
        List<String> stringList = new LinkedList<>(Arrays.asList(receivedFromFis.split("[|]")));

        return stringList;
    }


    private static int getIndex(List<String> stringList, String key) {
        ListIterator<String> iterator = stringList.listIterator();
        int index = -1;
        while (iterator.hasNext()) {
            String currentStr = iterator.next();
            if (currentStr.contains(key)) {
                index = iterator.nextIndex() - 1;
                break;
            }
        }
        return index;
    }

    public static String getValue(List<String> stringList, String key) {
        String value = "";

        int index = getIndex(stringList, key);
        String valueFromIndex = stringList.get(index);
        String[] pair = valueFromIndex.split("=");
        if (pair[0].equals(key)) {
            value = pair[1];
        } else {
            value = null;
        }

        return value;
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

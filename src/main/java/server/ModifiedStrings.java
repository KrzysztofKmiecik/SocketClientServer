package server;

import java.util.*;

import static java.util.Collections.EMPTY_LIST;

public class ModifiedStrings {

    public static String getMyModifiedStringWithPrefix(final List<String> prefixes, final String receivedFromFis) {
        String myModifiedString;
        if (receivedFromFis.contains("BCNF")) {
            for (String prefix : prefixes) {
                if (receivedFromFis.contains("id=" + prefix)) {
                    myModifiedString = listToStringWithSeparator(prepareFinalList(receivedFromFis), "|");
                    return myModifiedString;
                }
            }
        }
        return receivedFromFis;
    }

    public static List<String> prepareFinalList(final String receivedFromFis) {
        final List<String> stringList;
        final List<String> finalList;
        stringList = addIDTest(receivedFromFis);
        finalList = addMap(stringList);
        return finalList;
    }

    public static List<String> addIDTest(final String receivedFromFis) {
        final List<String> stringList = convertFISResponseToList(receivedFromFis);
        final int index = getIndex(stringList, "id");
        StringBuilder stringToAdd=new StringBuilder("id=");
        if (index >= 0) {
            String prefix=getValue(stringList,"id").substring(0,2);
            stringToAdd.append(prefix);
            stringToAdd.append("ZZZZZ");
            stringList.add(index + 1, stringToAdd.toString());
        }
        return stringList;
    }

    public static List<String> addMap(final List<String> receivedFromFisList) {
        List<String> finalList = new ArrayList<>(receivedFromFisList);
        final int mapIndex = getIndex(receivedFromFisList, "map");
        if (mapIndex >= 0) {
            String mapValue = getValue(receivedFromFisList, "map");
            StringBuilder newMapValue = new StringBuilder(mapValue);
            newMapValue.append("0");
            newMapValue.insert(0, "map=");
            final String finalString = newMapValue.toString();
            finalList.set(mapIndex, finalString);
        }
        return finalList;
    }

    public static List<String> addMapWithCopy(final List<String> receivedFromFisList) {
        List<String> finalList = new ArrayList<>(receivedFromFisList);
        final int mapIndex = getIndex(receivedFromFisList, "map");
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
            final String finalString = newMapValue.toString();
            finalList.set(mapIndex, finalString);
        }
        return finalList;
    }

    public static List<String> convertFISResponseToList(final String receivedFromFis) {
        List<String> stringList;
        if (receivedFromFis != null) {
            stringList = new LinkedList<>(Arrays.asList(receivedFromFis.split("[|]")));
        } else {
            stringList = EMPTY_LIST;
        }
        return stringList;
    }


    private static int getIndex(final List<String> stringList, final String key) {
        ListIterator<String> iterator = stringList.listIterator();
        int index = -1;
        if (key != null) {
            while (iterator.hasNext()) {
                String currentStr = iterator.next();
                if (currentStr.contains(key)) {
                    index = iterator.nextIndex() - 1;
                    break;
                }
            }
        }
        return index;
    }

    public static String getValue(final List<String> stringList, final String key) {
        String value = "";
        if (key != null) {
            final int index = getIndex(stringList, key);
            if (index >= 0) {
                final String valueFromIndex = stringList.get(index);
                final String[] pair = valueFromIndex.split("=");
                if (pair[0].equals(key)) {
                    value = pair[1];
                }
            }

        }
        return value;
    }


    public static String listToStringWithSeparator(final List<String> stringList, final String separator) {
        StringBuilder returnStr = new StringBuilder();
        if (separator != null) {
            for (int i = 0; i < stringList.size(); i++) {
                if (i == stringList.size() - 1) {
                    returnStr.append(stringList.get(i));
                } else {
                    returnStr.append(stringList.get(i));
                    returnStr.append(separator);
                }
            }
            returnStr.append("\n");
        }
        return returnStr.toString();
    }

}

package server;

import client.IPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class IPServerClient {


    private final int portNumber;

    private IPClient FisClient;

    public static IPServerClient with(final int portNumber, final IPClient FisClient) {
        return new IPServerClient(portNumber, FisClient);
    }

    private IPServerClient(final int portNumber, final IPClient FisClient) {
        this.portNumber = portNumber;
        this.FisClient = FisClient;
    }

    public void connect() {
        try (
                ServerSocket serverSocket = new ServerSocket(this.portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String receivedFromFis = FisClient.sendAndReceiveIPMessage(in.readLine());
            String myModifiedString = getMyModifiedString(receivedFromFis);
            out.println(myModifiedString);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
            // e.printStackTrace();
        }
    }


    public String getMyModifiedString(final String receivedFromFis) {

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
                System.out.println();
            }
        }

        stringList.add(index, "id=test");

        String current = "";
        current = listToStringWithSeparator(stringList, "|");
        return current;
    }

    private String listToStringWithSeparator(final List<String> stringList, final String separator) {

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

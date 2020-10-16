package server;

import client.IPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class IPServerClient {

    private final int portNumber;
    private Logger logger = LoggerFactory.getLogger(IPServerClient.class);

    private IPClient FisClient;
    private String[] prefixes;

    public static IPServerClient with(final int portNumber, final IPClient FisClient,final String[] prefixes) {
        return new IPServerClient(portNumber, FisClient,prefixes);
    }

    private IPServerClient(final int portNumber, final IPClient FisClient,final String[] prefixes) {
        this.portNumber = portNumber;
        this.FisClient = FisClient;
        this.prefixes=prefixes;
    }

    public void connect() {
        try (
                ServerSocket serverSocket = new ServerSocket(this.portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            final List<String> prefixes = Arrays.asList(this.prefixes);
            String receivedFromClient = in.readLine();
            String receivedFromFis = FisClient.sendAndReceiveIPMessage(receivedFromClient);
            String myModifiedString = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes, receivedFromClient, receivedFromFis);
            out.println(myModifiedString);
            logger.info("JavaServer received from FIS: " + receivedFromFis);
            logger.info("JavaServer sent to milling " + myModifiedString);
        } catch (IOException e) {
            logger.error("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            logger.error(e.getMessage());
        }
    }

    public void connectLoop(){
        while (true){
            this.connect();
        }
    }


}

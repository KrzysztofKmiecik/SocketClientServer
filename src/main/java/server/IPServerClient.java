package server;

import client.Client;
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

public class IPServerClient implements ServerClient {

    private final int portNumber;
    private final Logger logger = LoggerFactory.getLogger(IPServerClient.class);

    private final Client FisClient;
    private final String[] prefixes;
    private static final String EMPTY_STRING = "";

    public static IPServerClient with(final int portNumber, final Client FisClient, final String[] prefixes) {
        return new IPServerClient(portNumber, FisClient, prefixes);
    }

    private IPServerClient(final int portNumber, final Client FisClient, final String[] prefixes) {
        this.portNumber = portNumber;
        this.FisClient = FisClient;
        this.prefixes = prefixes;
    }

    @Override
    public void connect() {
        logger.info("START");
        try (
                ServerSocket serverSocket = new ServerSocket(this.portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            final List<String> prefixes = Arrays.asList(this.prefixes);
            final String receivedFromClient = in.readLine();
            logger.info("JavaServer received from milling: " + receivedFromClient);
            String receivedFromFis = FisClient.sendAndReceiveIPMessage(receivedFromClient);
            logger.info("JavaServer received from FIS: " + receivedFromFis);
            String myModifiedString = ModifiedStrings.getMyModifiedStringWithPrefix(prefixes, receivedFromFis);
            logger.info("JavaServer is going to send to milling " + myModifiedString);
            if ((receivedFromFis != null) && (!EMPTY_STRING.equals(myModifiedString))) {
                out.println(myModifiedString);
                logger.info("JavaServer sent to milling " + myModifiedString);
            }

        } catch (IOException e) {
            logger.error("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            logger.error(e.getMessage());
        }
        logger.info("STOP");
    }

    @Override
    public void connectLoop() {
        while (true) {
            this.connect();
        }
    }


}

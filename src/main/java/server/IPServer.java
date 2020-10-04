package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class IPServer {

    private final int portNumber;

    public IPServer(int portNumber) {
        this.portNumber = portNumber;
    }

    public void connect(String replayMessage) {
        try (
                ServerSocket serverSocket = new ServerSocket(this.portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {

            out.println(replayMessage);

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
            // e.printStackTrace();
        }

    }
}

package client;

import java.io.*;
import java.net.*;


public class IPClient {

    private String hostIP;
    private int hostPort;

    public IPClient(String hostIP, int hostPort) {
        this.hostIP = hostIP;
        this.hostPort = hostPort;
    }

    public String sendAndReceiveIPMessage(String message) {
        String str = "";
        try (
                Socket mySocket = new Socket(hostIP, hostPort);
                PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        ) {
            out.println(message);
            str = in.readLine();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostIP);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostIP);
            System.exit(1);
        }
        return str;
    }
}

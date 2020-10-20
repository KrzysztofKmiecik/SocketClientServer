package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;


public class IPClient {

    private String hostIP;
    private int hostPort;

    private Logger logger= LoggerFactory.getLogger(IPClient.class);


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
            logger.info("Milling simulation_"+message);
            out.println(message);
            str = in.readLine();
            logger.info(str);
        } catch (UnknownHostException e) {
            logger.error("Don't know about host " + hostIP);
            System.exit(1);
        } catch (IOException e) {
            logger.error("Couldn't get I/O for the connection to " +
                    hostIP);
            System.exit(1);
        }
        return str;
    }
}

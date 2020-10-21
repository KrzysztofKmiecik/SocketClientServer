package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class IPClient implements Client {

    private final String hostIP;
    private final int hostPort;

    private final Logger logger = LoggerFactory.getLogger(IPClient.class);


    public IPClient(String hostIP, int hostPort) {
        this.hostIP = hostIP;
        this.hostPort = hostPort;
    }

    @Override
    public String sendAndReceiveIPMessage(String message) {
        String str = "";
        try (
                Socket mySocket = new Socket(hostIP, hostPort);
                PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()))
        ) {
            logger.info("Milling simulation_" + message);
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

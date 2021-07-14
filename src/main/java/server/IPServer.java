package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class IPServer implements Server {

    private final int portNumber;
    private final Logger logger = LoggerFactory.getLogger(IPServer.class);

    public IPServer(int portNumber) {
        this.portNumber = portNumber;
    }

    @Override
    public void connect(String replayMessage) {
        try (
                ServerSocket serverSocket = new ServerSocket(this.portNumber);
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {

            out.println(replayMessage);
            logger.info(replayMessage);

        } catch (IOException e) {
            logger.error("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            logger.error(e.getMessage());
        }
    }
}

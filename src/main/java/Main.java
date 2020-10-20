import client.IPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.IPServerClient;

public class Main {


    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);


       /* if (args.length > 1) {
            final String ip = args[0];
            final int port = Integer.parseInt(args[1]);*/


            /*//FIS simulation
            IPServer FisServer = new IPServer(5555);
            FisServer.connect("FIS BCNF");*/

       /* //Java Server
        System.out.println("Hello I'm JavaServer waiting for connection");
        String[] prefixes ={"5S", "6S", "7S", "5T", "6T", "7T"};
        IPServerClient javaServer = IPServerClient.with(4444, new IPClient("10.235.241.235", 24405),prefixes);
        javaServer.connectLoop();*/


        //Milling client
        System.out.println("Hello I'm Client");
        // IPClient millingClient = new IPClient("eoltserwer", 4444);
        //  IPClient millingClient = new IPClient("10.235.244.57", 4444);
        IPClient millingClient = new IPClient("localhost", 4444);
        String sendMessage = "BREQ|process=MILLING|station=SMT_MILLING_1|id=7SZZZZZ";
        String millingResponse = millingClient.sendAndReceiveIPMessage(sendMessage);
        logger.info("Milling Sent: " + sendMessage);
        logger.info("Milling received: " + millingResponse);


      /*  } else {
            logger.error("usage Main <IP> <Port>");
       }*/


    }


}

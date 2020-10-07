import client.IPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.IPServerClient;

public class Main {


    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        System.out.println("Hello I'm JavaServer waiting for connection");

       /* if (args.length > 1) {
            final String ip = args[0];
            final int port = Integer.parseInt(args[1]);*/


            /*//FIS simulation
            IPServer FisServer = new IPServer(5555);
            FisServer.connect("FIS BCNF");*/

     /*   //Java Server
        IPServerClient javaServer = IPServerClient.with(4444, new IPClient("10.235.241.235", 24405));
        javaServer.connect();*/

        //Milling client
        IPClient millingClient = new IPClient("eoltserwer", 4444);
        String sendMessage = "BREQ|process=MILLING|station=SMT_MILLING_1|id=7DG1ID6X";
        String millingResponse = millingClient.sendAndReceiveIPMessage(sendMessage);
        logger.info("Milling Sent: " + sendMessage);
        logger.info("Milling received: " + millingResponse);

      /*  } else {
            logger.error("usage Main <IP> <Port>");
       }*/


    }


}

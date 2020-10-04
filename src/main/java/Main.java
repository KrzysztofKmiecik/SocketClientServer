import client.IPClient;
import server.IPServer;
import server.IPServerClient;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");

        //FIS simulation
        IPServer FisServer = new IPServer(5555);
        FisServer.connect("FIS BCNF");

        //Java Server
        IPServerClient javaServer =IPServerClient.with(4444, new IPClient("127.0.0.1", 5555));
        javaServer.connect();

        //Milling
        IPClient millingClient = new IPClient("127.0.0.1", 4444);
        millingClient.sendAndReceiveIPMessage("BREQ");


    }


}

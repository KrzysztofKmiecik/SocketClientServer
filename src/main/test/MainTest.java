import client.IPClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest {

    @Test
    public void client() {
        Logger logger = LoggerFactory.getLogger(MainTest.class);
        logger.info("Hello I'm Client");
        // final IPClient millingClient = new IPClient("eoltserwer", 4444);
        final client.Client millingClient = new IPClient("10.235.244.57", 4444);
        // final IPClient millingClient = new IPClient("localhost", 4444);
        final String sendMessage = "BREQ|process=MILLING|station=SMT_MILLING_1|id=7SA003C";
        final String millingResponse = millingClient.sendAndReceiveIPMessage(sendMessage);
        logger.info("Milling Sent: " + sendMessage);
        logger.info("Milling received: " + millingResponse);
    }
}
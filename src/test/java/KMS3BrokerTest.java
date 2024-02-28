
public class KMS3BrokerTest {
    
    // static final Logger logger = Loggers.getLogger(KMS3BrokerTest.class);

    // final static int testPort = 8007;
    // static TCPServer kma3Server;


    // @BeforeAll
    // static void serverStartup() throws Exception {
    //     logger.info("KMS3BrokerTest > server start up");
    //     TCPServer.create().bind(testPort);
    // }

    // @Test
    // public void testKMA3TcpServer() throws Exception {
    //     logger.info("Testing testKMA3TcpServer");
    //     KMS3Client client = new KMS3Client("localhost", testPort);
    //     client.run();

    //     if(client.isAvailable() == false){
    //         Thread.sleep(1);
    //     }

    //     client.send(KMA3MessageRawBytes.singleMessage);
    //     client.close();
    // }

    // @Test
    // public void testKMA3BrokerWithBytePadding() throws Exception {
    //     logger.info("Testing testKMA3BrokerWithBytePadding");

    //     KMS3Client client = new KMS3Client("localhost", testPort);
    //     client.run();

    //     if(client.isAvailable() == false){
    //         Thread.sleep(1);
    //     }

    //     client.send(KMA3MessageRawBytes.testMessageWithPaddings);
        
    //     client.close();
    // }

    // @Test
    // public void testKMA3BrokerWithByteGarbeges() throws Exception {
    //     logger.info("Testing testKMA3BrokerWithBytePadding");

    //     KMS3Client client = new KMS3Client("localhost", testPort);
    //     client.run();

    //     if(client.isAvailable() == false){
    //         Thread.sleep(1);
    //     }

    //     client.send(KMA3MessageRawBytes.testMessageWithGarbeges);
        
    //     client.close();
    // }

}

package kr.intellectus.app;

public class Server {
    
    // static final kr.intellectus.util.Logger logger = Loggers.getLogger(Server.class);
    static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(KMA3Broker.class);


    private final static int PORT = 20000;

    public static void main(String[] args) throws Exception {

        logger.info("Server starting up");

        TCPServer.create().bind(PORT);
    }
}

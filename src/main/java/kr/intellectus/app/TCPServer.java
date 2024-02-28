package kr.intellectus.app;

public abstract class TCPServer {

    public static TCPServer create() {

        return TCPServerDafult.INSTANCE;
    }

    abstract public TCPServer bind(final int portNumber) throws InterruptedException;
    
}

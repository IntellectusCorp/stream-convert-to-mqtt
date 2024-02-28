package kr.intellectus.client;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import kr.intellectus.protocols.kms3.KMS3Encoder;
import kr.intellectus.util.Loggers;

public class KMS3Client implements AutoCloseable, Runnable {

    private EventLoopGroup eventLoopGroup;
    // private Bootstrap bootstrap;
    private ChannelHandler channelHandler;
    private KMS3ClientChannelHandler kms3Handler;
    private String host;
    private int port;

    static final kr.intellectus.util.Logger logger = Loggers.getLogger(KMS3Client.class);

    public KMS3Client(final String host, final int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        this.channelHandler = new KMS3ClientChannelHandler();
        this.kms3Handler = (KMS3ClientChannelHandler) this.channelHandler;

        this.eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(
                                    new KMS3Encoder(),
                                    channelHandler);
                        }
                    });
            bootstrap.connect(this.host, this.port).sync();
            
        } catch (InterruptedException interrupt) {
            interrupt.printStackTrace();
        }
    }

    public boolean isAvailable() {

        logger.info("Healthcheck client thread... "+ this.kms3Handler);

        if (this.kms3Handler == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public void send(byte[] message) {

        logger.info("Sending message from the client...");

        try {
            this.kms3Handler.sendMessage(message);

        } catch (Exception interrupt) {
            interrupt.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        this.eventLoopGroup.shutdownGracefully(1, 3, TimeUnit.SECONDS);
    }

}

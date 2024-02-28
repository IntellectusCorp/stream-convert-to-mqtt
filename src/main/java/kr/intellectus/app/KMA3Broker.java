package kr.intellectus.app;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import us.int2.server.ServerHandler;
import us.int2.util.ServerUtil;

public class KMA3Broker implements AutoCloseable {

    // static final kr.intellectus.util.Logger logger = Loggers.getLogger(KMA3Broker.class);
    static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(KMA3Broker.class);


    static final boolean SSL = System.getProperty("ssl") != null;

    private EventLoopGroup bootGroup;
    private EventLoopGroup workerGroup;

    private int port;

    public KMA3Broker(final int port) {
        this.port = port;
    }

    public void start() {
        try {
            final SslContext sslCtx = ServerUtil.buildSslContext();

            this.bootGroup = new NioEventLoopGroup(1);
            this.workerGroup = new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap();

            logger.info("Server Bootstrapping");
            
            bootstrap.group(bootGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            if (sslCtx != null) {
                                logger.info("INFO: add ssl handler to the pipeline");
                                pipeline.addLast(sslCtx.newHandler(ch.alloc()));
                            }
                            pipeline.addLast(
                                    new KMA3MessageDecoder(),
                                    new ServerHandler());
                            logger.info("add ServerHandler with Object Enc/Dec to the pipeline");
                        };
                    });
           
            bootstrap.bind(port).sync();

            logger.info("Server port bindind has done with " + port);

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public void close() {
        this.bootGroup.shutdownGracefully();
        this.workerGroup.shutdownGracefully();
    }

}

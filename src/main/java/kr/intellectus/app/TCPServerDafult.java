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

final class TCPServerDafult extends TCPServer {

    static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(KMA3Broker.class);
    
    static final TCPServerDafult INSTANCE = new TCPServerDafult();

    private ServerBootstrap bootstrap;

    TCPServerDafult() {

        logger.info("Creating new TCPServerDafult instnace.");

        final SslContext sslCtx = ServerUtil.buildSslContext();

        EventLoopGroup bootGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        this.bootstrap = new ServerBootstrap();

        ServerHandler handler = new ServerHandler();
        // handler.addDB(AWSTimestream.INSTANCE);

        this.bootstrap.group(bootGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            if (sslCtx != null) {                                
                                pipeline.addLast(sslCtx.newHandler(ch.alloc()));
                            }

                            pipeline.addLast(
                                    new KMA3MessageDecoder(),
                                    handler);
                        };
                        
                    });
    }

    @Override
    public TCPServer bind(final int portNumber) throws InterruptedException {
        this.bootstrap.bind(portNumber).sync();
        return this;
    }

    
    

}

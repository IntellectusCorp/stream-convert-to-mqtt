package kr.intellectus.client;

import static io.netty.channel.ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class KMS3ClientChannelHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext context = null;
    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(KMS3ClientChannelHandler.class);

    public void sendMessage(byte[] message) throws Exception {

        if (this.context == null) {
            logger.info("error: context == null");
            throw new Exception("ChannelHandlerContext is null");
        }

        ChannelFuture future = this.context.writeAndFlush(message)
                .addListener(FIRE_EXCEPTION_ON_FAILURE)
                .sync();

        logger.info("KMS3ClientChannelHandler: ");
        logger.info(future.state().toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        if (this.context == null) {
            this.context = ctx;
        }

        System.out.println(" KMS3ClientHandler Channel Activated");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Echo back the received object to the server.
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

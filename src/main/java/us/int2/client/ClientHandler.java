package us.int2.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import static io.netty.channel.ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private final byte[] testMessage = new byte[7];

    public ClientHandler() {

        testMessage[0] = (byte) 0xFA;
        testMessage[1] = (byte) 0xFB;
        testMessage[2] = 24;
        testMessage[3] = 4;
        testMessage[4] = 16;
        testMessage[5] = (byte) 0xFF;
        testMessage[6] = (byte) 0xFE;

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // Send the first message if this handler is a client-side handler.

        
        ChannelFuture future = ctx.writeAndFlush(testMessage);
        future.addListener(FIRE_EXCEPTION_ON_FAILURE); // Let object serialisation exceptions propagate.
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

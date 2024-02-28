package us.int2.client;

// import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class KMSEncoder extends MessageToByteEncoder<byte[]>{

    @Override
    protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out) throws Exception {
        
        // byte[] encoded = msg.getBytes(StandardCharsets.UTF_8);
        out.writeBytes(msg);
        
    }
}

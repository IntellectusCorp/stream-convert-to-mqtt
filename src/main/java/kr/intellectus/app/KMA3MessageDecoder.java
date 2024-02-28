package kr.intellectus.app;

import java.util.List;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class KMA3MessageDecoder extends ByteToMessageDecoder {

    // static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(KMA3MessageDecoder.class);
    static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(KMA3MessageDecoder.class);


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in.readableBytes() < 2) {
            return;
        }
        // final int bufferLength = in.readableBytes();

        ByteBuf duplicated = in.duplicate();

        byte[] header = new byte[2];
        duplicated.readBytes(header);

        if (header[0] != (byte) 0xFA || header[1] != (byte) 0xFB) {
            byte passed = in.readByte();
            // logger.info("passed byte: ", String.format("%02X " + passed));
            logger.info(String.valueOf(passed));
            return;
        }

        if(duplicated.readableBytes() < 151) {
            return;
        }
        
        duplicated.readBytes(149);

        byte[] protocolEndFlag = new byte[2];
        duplicated.readBytes(protocolEndFlag);

        // logger.info(String.format("%02X", protocolEndFlag[0] & 0xFF)+String.format("%02X", protocolEndFlag[1] & 0xFF));

        if (protocolEndFlag[0] == (byte) 0xFF && protocolEndFlag[1] == (byte) 0xFE) {
            byte[] messageBytes = new byte[153];
            in.readBytes(messageBytes);

            logger.info("Building message");
            // out.add(message);
        } else {
            in.readBytes(header);
        }

        // byte[] protocolEndFlag = new byte[2];
        // for (int offset = 1; duplicated.isReadable(2); offset += 1) {

        //     duplicated.getBytes(offset, protocolEndFlag);

        //     if (protocolEndFlag[0] == (byte) 0xFF && protocolEndFlag[1] == (byte) 0xFE) {

        //         final int checkpoint = offset + 2;

        //         byte[] decoded = new byte[checkpoint];
        //         in.readBytes(decoded);

        //         logger.info("Confirmed header and end flags with message size: " + decoded.length);

        //         ObservationMessage message = ObservationMessage.builder()
        //             .build();
        //         logger.info(message.printFullMessage());

        //         out.add(message);

        //         break;
        //     }
        // }
    }

}

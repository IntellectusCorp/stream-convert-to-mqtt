package us.int2.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;
import kr.intellectus.biz.kma3.ObservationMessage;
import kr.intellectus.infra.AWSTimestream;

@Sharable
public class ServerHandler  extends ChannelInboundHandlerAdapter {

    // static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(ServerHandler.class);
    static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ServerHandler.class);

    private AWSTimestream timestream;

    public ServerHandler() {
        super();

        this.timestream = AWSTimestream.INSTANCE;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ObservationMessage msgObject = (ObservationMessage) msg;
        timestream.writeRecordsWithUpsert(msgObject);
        logger.info("(ServerHandler) Received: " + msgObject.printFullMessage());

        ctx.flush();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }   

}

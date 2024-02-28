package data;
import java.time.LocalDateTime;

import kr.intellectus.biz.kma3.DateTime;
import kr.intellectus.biz.kma3.ProtocolVersion;
import software.amazon.awssdk.thirdparty.jackson.core.util.ByteArrayBuilder;

public class KMA3MessageRawBytes {

    public static final byte[] protocolStartFlag = new byte[2];
    static {
        protocolStartFlag[0] = (byte) 0xFA;
        protocolStartFlag[1] = (byte) 0xFB;
    }

    public static final byte[] protocolEndFlag = new byte[2];
    static {
        protocolEndFlag[0] = (byte) 0xFF;
        protocolEndFlag[1] = (byte) 0xFE;
    }

    private static final ProtocolVersion version = new ProtocolVersion(24, 4, 22);
    private static final DateTime dateTime = new DateTime(LocalDateTime.now());

    private static final ByteArrayBuilder singleMessageBuilder = new ByteArrayBuilder();
    static {
        singleMessageBuilder.write(protocolStartFlag);
        singleMessageBuilder.write(version.getRawBytes());
        singleMessageBuilder.write(dateTime.getRawBytes());
        singleMessageBuilder.write(protocolEndFlag);
    }

    public static byte[] singleMessage = singleMessageBuilder.toByteArray();

    public static final byte[] testMessageWithPaddings = new byte[15];
    static {
        testMessageWithPaddings[0] = (byte) 0xFA;
        testMessageWithPaddings[1] = (byte) 0xFB;
        testMessageWithPaddings[2] = 24;
        testMessageWithPaddings[3] = 4;
        testMessageWithPaddings[4] = 17;
        testMessageWithPaddings[5] = (byte) 0xFF;
        testMessageWithPaddings[6] = (byte) 0xFE;
        testMessageWithPaddings[7] = (byte) 0x00;
        testMessageWithPaddings[8] = (byte) 0xFA;
        testMessageWithPaddings[9] = (byte) 0xFB;
        testMessageWithPaddings[10] = 24;
        testMessageWithPaddings[11] = 4;
        testMessageWithPaddings[12] = 17;
        testMessageWithPaddings[13] = (byte) 0xFF;
        testMessageWithPaddings[14] = (byte) 0xFE;
    }

    public static final byte[] testMessageWithGarbeges = new byte[15];
    static {
        testMessageWithGarbeges[0] = 0;
        testMessageWithGarbeges[1] = 0;
        testMessageWithGarbeges[2] = 24;
        testMessageWithGarbeges[3] = 4;
        testMessageWithGarbeges[4] = 17;
        testMessageWithGarbeges[5] = 0;
        testMessageWithGarbeges[6] = 0;
        testMessageWithGarbeges[7] = 0;
        testMessageWithGarbeges[8] = 0;
        testMessageWithGarbeges[9] = 0;
        testMessageWithGarbeges[10] = 24;
        testMessageWithGarbeges[11] = 4;
        testMessageWithGarbeges[12] = 17;
        testMessageWithGarbeges[13] = 0;
        testMessageWithGarbeges[14] = 0;
    }
}

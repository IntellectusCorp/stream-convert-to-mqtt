package kr.intellectus.biz.kma3;

public class ObservationFormat {
    
    public static final int OFFSET_BYTES = 1;
    private final byte[] rawBytes = new byte[1];

    public ObservationFormat(byte bin) {
        rawBytes[0] = bin;
    }

    public byte[] getRawBytes() {
        return rawBytes;
    }
}

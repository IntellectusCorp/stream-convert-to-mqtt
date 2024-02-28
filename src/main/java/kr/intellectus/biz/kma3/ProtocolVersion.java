package kr.intellectus.biz.kma3;

public final class ProtocolVersion {

    public static final int OFFSET_BYTES = 3;

    private static final short DEFAULT_YEAR = (short) 24;
    private static final short DEFAULT_MONTH = (short) 1;
    private static final short DEFAULT_DAY = (short) 1;

    private final byte[] rawByte = new byte[OFFSET_BYTES];
    private final short year;
    private final short month;
    private final short day;

    public ProtocolVersion() {
        this(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY);
    }

    public ProtocolVersion(byte[] bytes) {
        rawByte[0] = bytes[0];
        year = (short) (bytes[0] & 0xff);

        rawByte[1] = bytes[1];
        month = (short) (bytes[1] & 0xff);

        rawByte[2] = bytes[2];
        day = (short) (bytes[2] & 0xff);
    }

    public ProtocolVersion(final int y, final int m, final int d) {
        this((short) y, (short) m, (short) d);
    }

    public ProtocolVersion(final short y, final short m, final short d) {
        rawByte[0] = (byte) (y & 0xFF);
        year = y;

        rawByte[1] = (byte) (m & 0xFF);
        month = m;

        rawByte[2] = (byte) (d & 0xFF);
        day = d;
    }

    public String printVersion() {
        return new StringBuilder().append(year).append(month).append(day).toString();
    }

    public byte[] getRawBytes() {
        return rawByte;
    }
}

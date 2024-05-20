package kr.intellectus.biz.kma3;

import java.time.LocalDateTime;

public class DateTime {

    static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DateTime.class);

    public static final int OFFSET_BYTES = 5;

    private final byte[] rawByte = new byte[OFFSET_BYTES];
    private short year;
    private short month;
    private short day;
    private short hours;
    private short minutes;

    public DateTime(byte[] bytes) {
        rawByte[0] = bytes[0];
        year = (short) (bytes[0] & 0xff);

        rawByte[1] = bytes[1];
        month = (short) (bytes[1] & 0xff);

        rawByte[2] = bytes[2];
        day = (short) (bytes[2] & 0xff);

        rawByte[3] = bytes[3];
        hours = (short) (bytes[3] & 0xff);

        rawByte[4] = bytes[4];
        minutes = (short) (bytes[4] & 0xff);
    }

    public DateTime(final short y, final short mon, final short d, final short h, final short min) {
        rawByte[0] = (byte) (y & 0xFF);
        year = y;

        rawByte[1] = (byte) (mon & 0xFF);
        month = mon;

        rawByte[2] = (byte) (d & 0xFF);
        day = d;

        rawByte[3] = (byte) (h & 0xFF);
        hours = h;

        rawByte[4] = (byte) (min & 0xFF);
        minutes = min;
    }

    public DateTime(LocalDateTime now) {
        this(
                (short) (now.getYear() - 2000),
                (short) now.getMonthValue(),
                (short) now.getDayOfMonth(),
                (short) now.getHour(),
                (short) now.getMinute());
    }

    public String print() {
        return new StringBuilder()
                .append(year)
                .append("-")
                .append(month)
                .append("-")
                .append(day)
                .append("-")
                .append(hours)
                .append(":")
                .append(minutes)
                .toString();
    }

    @Override
    public String toString() {
        return print();
    }

    public byte[] getRawBytes() {
        return rawByte;
    }

}

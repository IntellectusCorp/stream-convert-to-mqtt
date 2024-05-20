package kr.intellectus.biz.kma3.metrics;

import java.nio.ByteBuffer;

public abstract class Measurement implements Metric {

    static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Measurement.class);

    private final byte[] rawByte ;
    private final RepresentedValue represented;
    private final Metrics metric;

    private Measurement(final Metrics metric, final byte[] bytes, final RepresentedValue represented) {
        this.metric = metric;
        this.rawByte = bytes.clone();
        this.represented = represented;
    }

    public Measurement(final Metrics metric, final byte[] bytes, final int bitsOffset) {
        this(metric, bytes,new RepresentedValue(parseBytesToRepresentedValue(bytes, bitsOffset)));   
    }

    public Measurement(final Metrics metric, final int represented, final int bitsOffset) {
        this(metric, parseRepresentedToBytes(represented, bitsOffset), new RepresentedValue(represented));
    }

    public Measurement(final Metrics metric, final int represented, final byte[] bytes) {
        this(metric, bytes, new RepresentedValue(represented));
    }

    public byte[] getRawBytes() {
        return rawByte;
    }

    public String getMetricName() {
        return metric.getTerm();
    }

    public int getRepresentedValue() {
        return represented.representedValue;
    }

    private static  int parseBytesToRepresentedValue(byte[] bytes, int bitOffset) {

        // final int lowBitsOffest = 16 - bitOffset; 
        // final int highBitsOffset = 8 - lowBitsOffest; 
        // final int highBitsOffset = 8 - lowBitsOffest; 
        final int lowBitsOffest = 8; 

        // int highBits = (bytes[0] & 0xFF) << highBitsOffset;
        // int lowBits = (bytes[1] & 0xFF) >> lowBitsOffest;

        int highBits = (bytes[0] & 0xFF) << lowBitsOffest;
        int lowBits = (bytes[1] & 0xFF);

        return (highBits | lowBits);
    }

    private static byte[] parseRepresentedToBytes(final int represented, final int bitOffset) {
        
        // final int lowBitsOffest = 16 - bitOffset; 
        // final int highBitsOffset = 8 - lowBitsOffest;
        // final byte lowBitsMask = (byte) (( 1 << highBitsOffset)  - 1 );
        
        // byte[] parsed = new byte[2]; 

        // parsed[0] = (byte)((represented >> highBitsOffset) & 0xFF);
        // parsed[1] = (byte)((represented & lowBitsMask) << lowBitsOffest);

        // parsed[0] = (byte) represented >> 24;
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort((short) represented);

        return buffer.array();
    }

    private static class RepresentedValue {
        final int representedValue;

        RepresentedValue(int val) {
            representedValue = val;
        }
    }

}

package kr.intellectus.biz.kma3.metrics;

public abstract class Measurement implements Metric {

    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(Measurement.class);

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
        this(metric, bytes, represented);
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

        final int lowBitsOffest = 16 - bitOffset; 
        final int highBitsOffset = 8 - lowBitsOffest; 

        int highBits = (bytes[0] & 0xFF) << highBitsOffset;
        int lowBits = (bytes[1] & 0xFF) >>> lowBitsOffest;

        return (highBits | lowBits);
    }

    private static byte[] parseRepresentedToBytes(final int represented, final int bitOffset) {
        
        final int lowBitsOffest = 16 - bitOffset; 
        final int highBitsOffset = 8 - lowBitsOffest;
        final byte lowBitsMask = (byte) (( 1 << highBitsOffset)  - 1 );
        
        byte[] parsed = new byte[2]; 

        parsed[0] = (byte)((represented >> highBitsOffset) & 0xFF);
        parsed[1] = (byte)((represented & lowBitsMask) << lowBitsOffest);
        return parsed;
    }

    private static class RepresentedValue {
        final int representedValue;

        RepresentedValue(int val) {
            representedValue = val;
        }
    }

}
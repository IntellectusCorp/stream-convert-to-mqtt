package kr.intellectus.biz.kma3.metrics.groupa;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class Precipitation01 extends Measurement {

    static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Precipitation01.class);

    private final static Metrics METRIC = Metrics.PRECIPITATION01;
    private final static int BITS_OFFSET = 15;
    public final static int OFFSET_BYTES = 2;

    public Precipitation01(final byte[] bytes) {
        super(METRIC, bytes, BITS_OFFSET);
    }

    public Precipitation01(final int represented) {
        super(METRIC, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float) super.getRepresentedValue() / 10.0f;
        return String.valueOf(observedValue) ;
    }

    @Override
    public String getDataTypeString() {
        return MeasureValueType.DOUBLE.toString();
    }

    @Override
    public int getNumberOfByteUsed() {
        return OFFSET_BYTES;
    }
    
}

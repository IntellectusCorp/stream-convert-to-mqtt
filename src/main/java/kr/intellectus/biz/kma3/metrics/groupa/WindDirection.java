package kr.intellectus.biz.kma3.metrics.groupa;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class WindDirection extends Measurement {
    
    private final static int BITS_OFFSET  = 12;
    
    public static final int OFFSET_BYTES = 2;

    public WindDirection(final byte[] bytes) {
        super(Metrics.WIND_DIRECTION, bytes, BITS_OFFSET);
    }

    public WindDirection(final int represented) {
        super(Metrics.WIND_DIRECTION, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float) super.getRepresentedValue() / 10;
        return String.valueOf(observedValue) ;
    }

    @Override
    public int getNumberOfByteUsed() {
        return OFFSET_BYTES;
    }

    @Override
    public String getDataTypeString() {
        return MeasureValueType.DOUBLE.toString();
    }
}

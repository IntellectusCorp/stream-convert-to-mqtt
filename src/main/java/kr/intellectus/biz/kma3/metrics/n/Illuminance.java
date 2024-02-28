package kr.intellectus.biz.kma3.metrics.n;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class Illuminance extends Measurement {

    private final static Metrics metric = Metrics.ILLUMINANCE;
    public final static int OFFSET_BYTES = 2;
    
    private final static int BITS_OFFSET = 15;

    public Illuminance(final byte[] rawBytes) {
        super(metric, rawBytes, BITS_OFFSET);
    }

    public Illuminance(final int represented) {
        super(metric, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float)super.getRepresentedValue()  / 100.0f;
        return String.valueOf(observedValue);
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

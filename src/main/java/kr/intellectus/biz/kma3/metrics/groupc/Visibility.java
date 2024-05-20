package kr.intellectus.biz.kma3.metrics.groupc;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class Visibility extends Measurement {

    private final static Metrics metric = Metrics.VISIBILITY;
    private final static int BITS_OFFSET = 16;
    public final static int OFFSET_BYTES = 2;

    public Visibility(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public Visibility(final int represented) {
        super(metric, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        int observedValue = super.getRepresentedValue();
        return String.valueOf(observedValue);
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

package kr.intellectus.biz.kma3.metrics.groupc;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class PM25 extends Measurement {

    private final static Metrics metric = Metrics.PM25;
    private final static int BITS_OFFSET = 12;
    public final static int OFFSET_BYTES = 2;

    public PM25(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public PM25(final int represented) {
        super(metric, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float)super.getRepresentedValue()  / 10.0f;
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

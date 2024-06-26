package kr.intellectus.biz.kma3.metrics.groupc;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class CloudHeightLayer1 extends Measurement {

    private final static Metrics metric = Metrics.CLOUD_HEIGHT_LAYER1;
    private final static int BITS_OFFSET = 13;
    public final static int OFFSET_BYTES = 2;

    public CloudHeightLayer1(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public CloudHeightLayer1(final int represented) {
        super(metric, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        int observedValue = super.getRepresentedValue();
        return String.valueOf(observedValue);
    }

    @Override
    public String getDataTypeString() {
        return MeasureValueType.BIGINT.toString();
    }

    @Override
    public int getNumberOfByteUsed() {
        return OFFSET_BYTES;
    }
}

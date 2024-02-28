package kr.intellectus.biz.kma3.metrics.groupc;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class CloudHeightLayer3 extends Measurement {

    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(CloudHeightLayer3.class);

    private final static Metrics metric = Metrics.CLOUD_HEIGHT_LAYER3;
    private final static int BITS_OFFSET = 13;
    public final static int OFFSET_BYTES = 2;

    public CloudHeightLayer3(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public CloudHeightLayer3(final int represented) {
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

package kr.intellectus.biz.kma3.metrics.groupb;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class SunshineDuration extends Measurement {

    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(SunshineDuration.class);

    private final static Metrics metric = Metrics.SUNSHINE_DURATION;
    private final static int BITS_OFFSET = 16;
    public final static int OFFSET_BYTES = 2;

    public SunshineDuration(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public SunshineDuration(final int represented) {
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

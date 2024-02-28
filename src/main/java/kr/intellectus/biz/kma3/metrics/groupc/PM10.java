package kr.intellectus.biz.kma3.metrics.groupc;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class PM10 extends Measurement {

    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(PM10.class);

    private final static Metrics metric = Metrics.PM10;
    private final static int BITS_OFFSET = 12;
    public final static int OFFSET_BYTES = 2;

    public PM10(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public PM10(final int represented) {
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

package kr.intellectus.biz.kma3.metrics.groupb;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class SoilTemperature30cm extends Measurement {

    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(SoilTemperature30cm.class);

    private final static Metrics metric = Metrics.SOIL_TEMPERATURE_30CM;
    private final static int BITS_OFFSET = 11;
    public final static int OFFSET_BYTES = 2;

    public SoilTemperature30cm(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public SoilTemperature30cm(final int represented) {
        super(metric, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float)(super.getRepresentedValue() - 1000)  / 10.0f;
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

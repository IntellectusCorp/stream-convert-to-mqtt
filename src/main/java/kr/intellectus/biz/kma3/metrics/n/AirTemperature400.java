package kr.intellectus.biz.kma3.metrics.n;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class AirTemperature400 extends Measurement {

    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(AirTemperature400.class);

    private final static Metrics metric = Metrics.AIR_TEMPERATURE_400;
    private final static int BITS_OFFSET = 11;
    public final static int OFFSET_BYTES = 2;

    public AirTemperature400(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public AirTemperature400(final int represented) {
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

package kr.intellectus.biz.kma3.metrics.groupb;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature500cm;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class SoilTemperature500cm extends Measurement {

    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(SoilTemperature500cm.class);

    private final static Metrics metric = Metrics.SOIL_TEMPERATURE_500CM;
    private final static int BITS_OFFSET = 11;
    public final static int OFFSET_BYTES = 2;

    public SoilTemperature500cm(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public SoilTemperature500cm(final int represented) {
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

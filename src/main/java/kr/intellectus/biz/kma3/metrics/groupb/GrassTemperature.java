package kr.intellectus.biz.kma3.metrics.groupb;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class GrassTemperature extends Measurement {

    private final static Metrics metric = Metrics.GRASS_TEMPERATURE;
    private final static int BITS_OFFSET = 11;
    public final static int OFFSET_BYTES = 2;

    public GrassTemperature(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public GrassTemperature(final int represented) {
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

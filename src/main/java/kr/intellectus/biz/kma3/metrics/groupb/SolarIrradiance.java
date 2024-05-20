package kr.intellectus.biz.kma3.metrics.groupb;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class SolarIrradiance extends Measurement {

    private final static Metrics metric = Metrics.SOLAR_IRRADIANCE;
    private final static int BITS_OFFSET = 15;
    public final static int OFFSET_BYTES = 2;

    public SolarIrradiance(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public SolarIrradiance(final int represented) {
        super(metric, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float)super.getRepresentedValue() / 100.00f;
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

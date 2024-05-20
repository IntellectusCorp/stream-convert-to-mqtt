package kr.intellectus.biz.kma3.metrics.groupc;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class RadiationTotalSky extends Measurement {

    private final static Metrics metric = Metrics.RADIATION_TOTAL_SKY;
    private final static int BITS_OFFSET = 15;
    public final static int OFFSET_BYTES = 2;

    public RadiationTotalSky(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public RadiationTotalSky(final int represented) {
        super(metric, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float)(super.getRepresentedValue() - 10000)  / 10.0f;
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

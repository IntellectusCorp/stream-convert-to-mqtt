package kr.intellectus.biz.kma3.metrics.groupa;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class Precipitation0510 extends Measurement {

    private final static Metrics METRIC = Metrics.PRECIPITATION0510;
    private final static int BITS_OFFSET = 15;
    public final static int OFFSET_BYTES = 2;

    public Precipitation0510(byte[] bytes) {
        super(METRIC, bytes, BITS_OFFSET);
    }

    public Precipitation0510(final int represented) {
        super(METRIC, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float) super.getRepresentedValue() / 10.0f;
        return String.valueOf(observedValue) ;
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

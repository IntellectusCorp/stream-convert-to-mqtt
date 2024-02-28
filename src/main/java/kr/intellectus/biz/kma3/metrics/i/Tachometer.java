package kr.intellectus.biz.kma3.metrics.i;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class Tachometer extends Measurement {

    public static final int OFFSET_BYTES = 2;
    private static final int BITS_OFFSET = 13;
    private static final Metrics METRIC = Metrics.TACHOMETER;

    public Tachometer(final byte[] rawBytes) {
        super(METRIC, rawBytes, BITS_OFFSET);
    }

    public Tachometer(final int represented) {
        super(METRIC, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        return String.valueOf(super.getRepresentedValue());
    }

    @Override
    public int getNumberOfByteUsed() {
        return OFFSET_BYTES;
    }

    @Override
    public String getDataTypeString() {
       return MeasureValueType.BIGINT.toString();
    }
    
}

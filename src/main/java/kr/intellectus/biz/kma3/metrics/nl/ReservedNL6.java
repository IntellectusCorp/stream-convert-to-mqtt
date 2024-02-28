package kr.intellectus.biz.kma3.metrics.nl;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class ReservedNL6 extends Measurement {

    private final static Metrics metric = Metrics.RESERVED_NL6;
    private final static int BITS_OFFSET = 16;
    public final static int OFFSET_BYTES = 2;

    public ReservedNL6(final byte[] rawBytes) {
        super(metric, rawBytes, BITS_OFFSET);
    }

    public ReservedNL6(final int represented) {
        super(metric, represented, BITS_OFFSET);
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

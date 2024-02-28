package kr.intellectus.biz.kma3.metrics.groupcl;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class ReservedCL4 extends Measurement {

    public static final int OFFSET_BYTES = 2;
    private static final int BITS_OFFSET = 16;
    private static final Metrics METRIC = Metrics.RESERVED_CL4;

    public ReservedCL4(final byte[] rawBytes) {
        super(METRIC, rawBytes, BITS_OFFSET);
    }

    public ReservedCL4(final int represented) {
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

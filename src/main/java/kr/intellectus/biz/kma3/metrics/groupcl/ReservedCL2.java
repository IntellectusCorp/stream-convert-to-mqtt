package kr.intellectus.biz.kma3.metrics.groupcl;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class ReservedCL2 extends Measurement {

    private final static Metrics metric = Metrics.RESERVED_CL2;
    private final static int BITS_OFFSET = 16;
    public final static int OFFSET_BYTES = 2;

    public ReservedCL2(final byte[] rawBytes) {
        super(metric, rawBytes, BITS_OFFSET);
    }

    public ReservedCL2(final int represented) {
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

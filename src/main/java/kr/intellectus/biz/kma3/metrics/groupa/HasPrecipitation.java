package kr.intellectus.biz.kma3.metrics.groupa;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class HasPrecipitation extends Measurement{

    private final static Metrics METRIC = Metrics.HAS_PRECIPITATION;
    private final static int BITS_OFFSET = 4;
    public final static int OFFSET_BYTES = 2;

    public HasPrecipitation(final byte[] bytes) {
        super(METRIC, bytes, BITS_OFFSET);
    }

    public HasPrecipitation(final int represneted) {
        super(METRIC, represneted, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        int observedValue = super.getRepresentedValue();
        if(observedValue == 0) {
            return String.valueOf(false);
        } else if (observedValue == 10) {
            return String.valueOf(true);
        } else {
            return null;
        }
    }

    @Override
    public String getDataTypeString() {
        return MeasureValueType.BOOLEAN.toString();
    }

    @Override
    public int getNumberOfByteUsed() {
        return OFFSET_BYTES;
    }
    
}

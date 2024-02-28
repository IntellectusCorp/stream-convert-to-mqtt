package kr.intellectus.biz.kma3.metrics.groupa;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class WindDirectionInstantaneous extends Measurement {

    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(WindDirectionInstantaneous.class);

    private final static Metrics METRIC = Metrics.WIND_DIRECTION_INSTANTANEOUS;
    private final static int BITS_OFFSET = 12;
    public final static int OFFSET_BYTES = 2;

    public WindDirectionInstantaneous(final byte[] bytes) {
        super(METRIC, bytes, BITS_OFFSET);
    }

    public WindDirectionInstantaneous(final int represented) {
        super(METRIC, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float) super.getRepresentedValue() / 10;
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

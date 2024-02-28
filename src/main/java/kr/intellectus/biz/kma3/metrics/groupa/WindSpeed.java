package kr.intellectus.biz.kma3.metrics.groupa;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class WindSpeed extends Measurement {

    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(WindSpeed.class);

    private final static Metrics metric = Metrics.WIND_SPEED;
    private final static int BITS_OFFSET = 10;
    public final static int OFFSET_BYTES = 2;


    public WindSpeed(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public WindSpeed(final int represented) {
        super(metric, represented, BITS_OFFSET);
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
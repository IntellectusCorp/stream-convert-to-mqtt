package kr.intellectus.biz.kma3.metrics.groupa;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class AirPressure extends Measurement{

    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(AirPressure.class);

    private final static Metrics metric = Metrics.AIR_PRESSURE;
    private final static int BITS_OFFSET = 14;

    public final static int OFFSET_BYTES = 2;


    public AirPressure(final byte[] bytes) {
        super(metric, bytes,  BITS_OFFSET);
    }

    public AirPressure(final int represented) {
        super(metric, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float) super.getRepresentedValue() / (float) 10;
        return String.valueOf(observedValue);
    }

    @Override
    public int getNumberOfByteUsed() {
        return OFFSET_BYTES;
    }

    @Override
    public String getDataTypeString() {
       return MeasureValueType.DOUBLE.toString();
    }

}

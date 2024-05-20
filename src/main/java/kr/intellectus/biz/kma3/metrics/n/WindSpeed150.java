package kr.intellectus.biz.kma3.metrics.n;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class WindSpeed150 extends Measurement {

    private final static Metrics metric = Metrics.WIND_SPEED_150;
    private final static int BITS_OFFSET = 10;
    public final static int OFFSET_BYTES = 2;


    public WindSpeed150(final byte[] bytes) {
        super(metric, bytes, BITS_OFFSET);
    }

    public WindSpeed150(final int represented) {
        super(metric, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float)super.getRepresentedValue()  / 10.0f;
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

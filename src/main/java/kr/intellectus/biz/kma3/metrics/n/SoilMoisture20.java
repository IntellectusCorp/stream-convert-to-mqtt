package kr.intellectus.biz.kma3.metrics.n;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class SoilMoisture20 extends Measurement {

    private final static Metrics metric = Metrics.SOIL_MOISTURE20;
    public final static int OFFSET_BYTES = 2;
    
    private final static int BITS_OFFSET = 10;

    public SoilMoisture20(final byte[] rawBytes) {
        super(metric, rawBytes, BITS_OFFSET);
    }

    public SoilMoisture20(final int represented) {
        super(metric, represented, BITS_OFFSET);
    }

    @Override
    public String getObservedValueString() {
        float observedValue = (float)super.getRepresentedValue()  / 10.0f;
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

package kr.intellectus.biz.kma3.metrics.x;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;

public class DeviceSensorStatus extends Measurement {

    public static final int OFFSET_BYTES = 8;
    private static final int BITS_OFFSET = 16;
    private static final Metrics METRIC = Metrics.DEVICE_SENSOR_STATUS;

    public DeviceSensorStatus(final byte[] rawBytes) {
        super(METRIC, 0, rawBytes);
    }

    public DeviceSensorStatus(final int represented) {
        super(METRIC, 0 , initBytes());
    }

    private static byte[] initBytes() {
        return new byte[OFFSET_BYTES];
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

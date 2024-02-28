package data;

import kr.intellectus.biz.kma3.metrics.Metric;
import kr.intellectus.biz.kma3.metrics.i.Tachometer;
import kr.intellectus.biz.kma3.metrics.x.DeviceSensorStatus;
// import kr.intellectus.biz.kma3.metrics.y.DeviceVoltageStatus;

public final class MetricGroupIXYTestData {

        public static byte[] BYTES_I = GroupL.getIBytes();
        public static byte[] BYTES_X = GroupL.getXBytes();
        public static byte[] BYTES_Y = GroupL.getYBytes();

        private static final class GroupL {
                
                static byte[] getIBytes() {
                        Metric tachometer = new Tachometer(8000);
                        return tachometer.getRawBytes();
                }

                static byte[] getXBytes() {
                        Metric deviceSensorStatus = new DeviceSensorStatus(0);
                        return deviceSensorStatus.getRawBytes();
                }

                static byte[] getYBytes() {
                        byte[] bytes = new byte[1];
                        bytes[0] = 0;
                        // Metric deviceVoltageStatus = new DeviceVoltageStatus(bytes);
                        return bytes;
                }

        }
}

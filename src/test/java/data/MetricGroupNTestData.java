package data;

import kr.intellectus.biz.kma3.metrics.Metric;
import kr.intellectus.biz.kma3.metrics.n.*;
import software.amazon.awssdk.thirdparty.jackson.core.util.ByteArrayBuilder;

public final class MetricGroupNTestData {

        public static byte[] BYTES = N.getBytes();

        private static final class N {
                static byte[] getBytes() {
                        ByteArrayBuilder testMessageBuilder = new ByteArrayBuilder();

                        Metric soilMoisture10 = new SoilMoisture10(1000);
                        testMessageBuilder.write(soilMoisture10.getRawBytes());

                        Metric soilMoisture20 = new SoilMoisture20(1000);
                        testMessageBuilder.write(soilMoisture20.getRawBytes());

                        Metric soilMoisture30 = new SoilMoisture30(1000);
                        testMessageBuilder.write(soilMoisture30.getRawBytes());

                        Metric soilMoisture50 = new SoilMoisture50(1000);
                        testMessageBuilder.write(soilMoisture50.getRawBytes());

                        Metric illuminance = new Illuminance(32767);
                        testMessageBuilder.write(illuminance.getRawBytes());

                        Metric windSpeed150 = new WindSpeed150(1000);
                        testMessageBuilder.write(windSpeed150.getRawBytes());

                        Metric windSpeed400 = new WindSpeed400(1000);
                        testMessageBuilder.write(windSpeed400.getRawBytes());

                        Metric windSpeedInstantaneous150 = new WindSpeedInstantaneous150(1000);
                        testMessageBuilder.write(windSpeedInstantaneous150.getRawBytes());

                        Metric windSpeedInstantaneous400 = new WindSpeedInstantaneous400(1000);
                        testMessageBuilder.write(windSpeedInstantaneous400.getRawBytes());

                        Metric airTemperature50 = new AirTemperature50(1000);
                        testMessageBuilder.write(airTemperature50.getRawBytes());

                        Metric airTemperature400 = new AirTemperature400(1000);
                        testMessageBuilder.write(airTemperature400.getRawBytes());

                        Metric relativeHumidity50 = new RelativeHumidity50(1000);
                        testMessageBuilder.write(relativeHumidity50.getRawBytes());

                        Metric relativeHumidity400 = new RelativeHumidity400(1000);
                        testMessageBuilder.write(relativeHumidity400.getRawBytes());

                        byte[] nBytes = new byte[testMessageBuilder.size()];
                        System.arraycopy(testMessageBuilder.toByteArray(), 0, nBytes, 0,
                                        testMessageBuilder.size());

                        testMessageBuilder.close();
                        return nBytes;
                }
        }
}

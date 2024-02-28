package data;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

import kr.intellectus.biz.kma3.DateTime;
import kr.intellectus.biz.kma3.metrics.Metric;
import kr.intellectus.biz.kma3.metrics.groupc.*;
import software.amazon.awssdk.thirdparty.jackson.core.util.ByteArrayBuilder;

public final class MetricGroupCTestData {

        public static final DateTime DATETIME = new DateTime(LocalDateTime.now(ZoneOffset.UTC));
        public static byte[] BYTES = GroupC.getBytes();

        private static final class GroupC {
                static byte[] getBytes() {
                        ByteArrayBuilder testMessageBuilder = new ByteArrayBuilder();

                        Metric cloudHeightLayer1 = new CloudHeightLayer1(8000);
                        testMessageBuilder.write(cloudHeightLayer1.getRawBytes());

                        Metric cloudHeightLayer2 = new CloudHeightLayer2(45678);
                        testMessageBuilder.write(cloudHeightLayer2.getRawBytes());

                        Metric cloudHeightLayer3 = new CloudHeightLayer3(1);
                        testMessageBuilder.write(cloudHeightLayer3.getRawBytes());

                        Metric cloudAmount = new CloudAmount(10);
                        testMessageBuilder.write(cloudAmount.getRawBytes());

                        Metric visibility = new Visibility(50000);
                        testMessageBuilder.write(visibility.getRawBytes());

                        Metric pm10 = new PM10(3599);
                        testMessageBuilder.write(pm10.getRawBytes());

                        Metric pm25 = new PM25(1);
                        testMessageBuilder.write(pm25.getRawBytes());

                        Metric radiationNet = new RadiationNet(511);
                        testMessageBuilder.write(radiationNet.getRawBytes());

                        Metric radiationTotalSky = new RadiationTotalSky(0);
                        testMessageBuilder.write(radiationTotalSky.getRawBytes());

                        Metric radiationReflected = new RadiationReflected(32767);
                        testMessageBuilder.write(radiationReflected.getRawBytes());

                        Metric radiationDirectSolar = new RadiationDirectSolar(16387);
                        testMessageBuilder.write(radiationDirectSolar.getRawBytes());

                        Metric currentWeatherConditions = new CurrentWeatherConditions(99);
                        testMessageBuilder.write(currentWeatherConditions.getRawBytes());

                        byte[] groupABytes = new byte[testMessageBuilder.size()];
                        System.arraycopy(testMessageBuilder.toByteArray(), 0, groupABytes, 0,
                                        testMessageBuilder.size());

                        testMessageBuilder.close();
                        return groupABytes;
                }
        }
}

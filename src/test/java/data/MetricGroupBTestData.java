package data;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

import kr.intellectus.biz.kma3.DateTime;
import kr.intellectus.biz.kma3.metrics.*;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature500cm;
import kr.intellectus.biz.kma3.metrics.groupb.GrassTemperature;
import kr.intellectus.biz.kma3.metrics.groupb.GroundTemperature;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature100cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature10cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature150cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature20cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature300cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature30cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature50cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature5cm;
import kr.intellectus.biz.kma3.metrics.groupb.SolarIrradiance;
import kr.intellectus.biz.kma3.metrics.groupb.SunshineDuration;
import software.amazon.awssdk.thirdparty.jackson.core.util.ByteArrayBuilder;

public final class MetricGroupBTestData {

        public static final DateTime DATETIME = new DateTime(LocalDateTime.now(ZoneOffset.UTC));
        public static byte[] BYTES = GroupB.getBytes();

        private static final class GroupB {
                static byte[] getBytes() {
                        ByteArrayBuilder testMessageBuilder = new ByteArrayBuilder();

                        Metric solarIrradiance = new SolarIrradiance(32767);
                        testMessageBuilder.write(solarIrradiance.getRawBytes());

                        Metric sunshineDuration = new SunshineDuration(65535);
                        testMessageBuilder.write(sunshineDuration.getRawBytes());

                        Metric groundTemperature = new GroundTemperature(2000);
                        testMessageBuilder.write(groundTemperature.getRawBytes());

                        Metric grassTemperature = new GrassTemperature(1999);
                        testMessageBuilder.write(grassTemperature.getRawBytes());

                        Metric soilTemp5 = new SoilTemperature5cm(1909);
                        testMessageBuilder.write(soilTemp5.getRawBytes());

                        Metric soilTemp10 = new SoilTemperature10cm(500);
                        testMessageBuilder.write(soilTemp10.getRawBytes());

                        Metric soilTemp20 = new SoilTemperature20cm(510);
                        testMessageBuilder.write(soilTemp20.getRawBytes());

                        Metric soilTemp30 = new SoilTemperature30cm(511);
                        testMessageBuilder.write(soilTemp30.getRawBytes());

                        Metric soilTemp50 = new SoilTemperature50cm(1414);
                        testMessageBuilder.write(soilTemp50.getRawBytes());

                        Metric soilTemp100 = new SoilTemperature100cm(586);
                        testMessageBuilder.write(soilTemp100.getRawBytes());

                        Metric soilTemp150 = new SoilTemperature150cm(1000);
                        testMessageBuilder.write(soilTemp150.getRawBytes());

                        Metric soilTemp300 = new SoilTemperature300cm(1001);
                        testMessageBuilder.write(soilTemp300.getRawBytes());

                        Metric soilTemp500 = new SoilTemperature500cm(999);
                        testMessageBuilder.write(soilTemp500.getRawBytes());

                        byte[] groupABytes = new byte[testMessageBuilder.size()];
                        System.arraycopy(testMessageBuilder.toByteArray(), 0, groupABytes, 0,
                                        testMessageBuilder.size());

                        testMessageBuilder.close();
                        return groupABytes;
                }
        }
}

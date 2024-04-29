package data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

import kr.intellectus.biz.kma3.DateTime;
import kr.intellectus.biz.kma3.metrics.Metric;
import kr.intellectus.biz.kma3.metrics.groupa.AirPressure;
import kr.intellectus.biz.kma3.metrics.groupa.AirTemperature;
import kr.intellectus.biz.kma3.metrics.groupa.HasPrecipitation;
import kr.intellectus.biz.kma3.metrics.groupa.Precipitation01;
import kr.intellectus.biz.kma3.metrics.groupa.Precipitation0510;
import kr.intellectus.biz.kma3.metrics.groupa.RelativeHumidity;
import kr.intellectus.biz.kma3.metrics.groupa.SnowfallAmount;
import kr.intellectus.biz.kma3.metrics.groupa.WindDirection;
import kr.intellectus.biz.kma3.metrics.groupa.WindDirectionInstantaneous;
import kr.intellectus.biz.kma3.metrics.groupa.WindSpeed;
import software.amazon.awssdk.thirdparty.jackson.core.util.ByteArrayBuilder;

public final class MetricGroupATestData {

        static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers
            .getLogger(MetricGroupATestData.class);

        public static final DateTime DATETIME = new DateTime(LocalDateTime.now(ZoneOffset.UTC));

        public static byte[] BYTES = GroupA.getBytes();

        private static final class GroupA {

                static byte[] getBytes() {

                        final Random random = new Random();
                        ByteArrayBuilder testMessageBuilder = new ByteArrayBuilder();

                        final AirTemperature airTemp = new AirTemperature(1199);
                        testMessageBuilder.write(airTemp.getRawBytes());

                        final Metric wd = new WindDirection(1000);
                        testMessageBuilder.write(wd.getRawBytes());

                        final Metric ws = new WindSpeed(1000);
                        testMessageBuilder.write(ws.getRawBytes());

                        final Metric wdi = new WindDirectionInstantaneous(1000);
                        testMessageBuilder.write(wdi.getRawBytes());

                        final Metric wsi = new AirPressure(1000);
                        testMessageBuilder.write(wsi.getRawBytes());

                        final Metric prec0510 = new Precipitation0510(1000);
                        testMessageBuilder.write(prec0510.getRawBytes());

                        final Metric ap = new AirPressure(random.nextInt(10072, 10081));
                        testMessageBuilder.write(ap.getRawBytes());

                        final Metric haspre = new HasPrecipitation(0);
                        testMessageBuilder.write(haspre.getRawBytes());

                        final Metric sfa = new SnowfallAmount(4095);
                        testMessageBuilder.write(sfa.getRawBytes());

                        final Metric rh = new RelativeHumidity(500);
                        testMessageBuilder.write(rh.getRawBytes());

                        final Metric prec01 = new Precipitation01(32767);
                        testMessageBuilder.write(prec01.getRawBytes());

                        byte[] groupABytes = new byte[testMessageBuilder.size()];
                        System.arraycopy(testMessageBuilder.toByteArray(), 0, groupABytes, 0, testMessageBuilder.size());

                        testMessageBuilder.close();
                        return groupABytes;
                }

        }

}

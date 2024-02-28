package data;

import kr.intellectus.biz.kma3.metrics.Metric;
import kr.intellectus.biz.kma3.metrics.Metrics;
import kr.intellectus.biz.kma3.metrics.MetricsGroup;
import kr.intellectus.biz.kma3.metrics.MetricsIndex;
import kr.intellectus.biz.kma3.metrics.groupcl.*;
import software.amazon.awssdk.thirdparty.jackson.core.util.ByteArrayBuilder;

public final class MetricGroupLTestData {

        static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers
                        .getLogger(MetricGroupLTestData.class);

        public static byte[] BYTES_CL = GroupL.getCLBytes();
        public static byte[] BYTES_NL = GroupL.getNLBytes();

        private static final class GroupL {
                static byte[] getCLBytes() {
                        ByteArrayBuilder testMessageBuilder = new ByteArrayBuilder();

                        Metric cl1 = new ReservedCL1(0);
                        testMessageBuilder.write(cl1.getRawBytes());

                        Metric cl2 = new ReservedCL2(0);
                        testMessageBuilder.write(cl2.getRawBytes());

                        Metric cl3 = new ReservedCL3(0);
                        testMessageBuilder.write(cl3.getRawBytes());

                        Metric cl4 = new ReservedCL4(0);
                        testMessageBuilder.write(cl4.getRawBytes());

                        byte[] groupABytes = new byte[testMessageBuilder.size()];
                        System.arraycopy(testMessageBuilder.toByteArray(), 0, groupABytes, 0,
                                        testMessageBuilder.size());

                        testMessageBuilder.close();
                        return groupABytes;
                }

                static byte[] getNLBytes() {
                        ByteArrayBuilder testMessageBuilder = new ByteArrayBuilder();

                        for (int i = 1; i < (MetricsGroup.NL.NUM_OF_METRICS+1); i++) {
                                Metrics metric = Metrics.fromIndex(MetricsIndex.fromGroupAndNumber(MetricsGroup.NL, i));
                                
                                try {
                                        Metric metricInstance = (Metric) metric.CLASS.getConstructor(int.class)
                                                        .newInstance(0);
                                        testMessageBuilder.write(metricInstance.getRawBytes());
                                } catch (Exception e) {
                                        logger.info("[getNLBytes] Error " + metric.TERM);
                                }
                        }

                        byte[] resultBytes = new byte[testMessageBuilder.size()];
                        System.arraycopy(testMessageBuilder.toByteArray(), 0, resultBytes, 0,
                                        testMessageBuilder.size());

                        testMessageBuilder.close();
                        return resultBytes;
                }
        }
}

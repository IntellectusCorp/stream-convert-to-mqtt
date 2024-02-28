import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

import data.MetricGroupATestData;
import data.MetricGroupBTestData;
import data.MetricGroupCTestData;
import data.MetricGroupIXYTestData;
import data.MetricGroupLTestData;
import data.MetricGroupNTestData;
import kr.intellectus.biz.kma3.DateTime;
import kr.intellectus.biz.kma3.ObservationFormat;
import kr.intellectus.biz.kma3.ObservationMessage;
import kr.intellectus.biz.kma3.ObservationType;
import kr.intellectus.biz.kma3.metrics.MetricsGroup;
import kr.intellectus.infra.AWSTimestream;

public class AWSTimestreamTest {

    static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(AWSTimestreamTest.class);

    @Test
    public void writeWithBuilderTest() throws Exception {

        DateTime dateTime = new DateTime(LocalDateTime.now(ZoneOffset.UTC));
                ObservationType type = new ObservationType((byte)0);
        ObservationFormat format = new ObservationFormat((byte)0);


        ObservationMessage message = ObservationMessage.builder()
                .setDateTime(dateTime)
                .setObservationType(type)
                .setObservationFormat(format)
                .setOberservationGroupMetricsBytes(MetricsGroup.A, MetricGroupATestData.BYTES)
                .setOberservationGroupMetricsBytes(MetricsGroup.B, MetricGroupBTestData.BYTES)
                .setOberservationGroupMetricsBytes(MetricsGroup.C, MetricGroupCTestData.BYTES)
                .setOberservationGroupMetricsBytes(MetricsGroup.CL, MetricGroupLTestData.BYTES_CL)
                .setOberservationGroupMetricsBytes(MetricsGroup.N, MetricGroupNTestData.BYTES)
                .setOberservationGroupMetricsBytes(MetricsGroup.NL, MetricGroupLTestData.BYTES_NL)
                .setOberservationGroupMetricsBytes(MetricsGroup.I, MetricGroupIXYTestData.BYTES_I)
                .setOberservationGroupMetricsBytes(MetricsGroup.X, MetricGroupIXYTestData.BYTES_X)
                .setOberservationGroupMetricsBytes(MetricsGroup.Y, MetricGroupIXYTestData.BYTES_Y)
                .build();

        logger.info(String.format("[writeWithBuilderTest] Message size(bytes): %d", message.getRawBytes().length));

        logger.info(message.printFullMessage());
        // logger.info(message.getObservationMetricsString());

        AWSTimestream timestream = AWSTimestream.INSTANCE;
        timestream.writeRecordsWithUpsert(message);
    }

    // @Test
    // public void testMultiple() throws Exception {
    //     for (int i = 0; i < 3600; i++) {
    //         writeWithBuilderTest();
    //         Thread.sleep(1000);
    //     }
    // }
}
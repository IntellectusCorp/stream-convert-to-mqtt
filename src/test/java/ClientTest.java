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
import kr.intellectus.client.KMS3Client;
import kr.intellectus.util.Logger;
import kr.intellectus.util.Loggers;

public class ClientTest {

    static final Logger logger = Loggers.getLogger(ClientTest.class);

    @Test
    public void test() throws Exception {
        logger.info("test");
        KMS3Client client = new KMS3Client("localhost", 20000);
        client.run();

        if (client.isAvailable() == false) {
            Thread.sleep(1);
        }

        DateTime dateTime = new DateTime(LocalDateTime.now(ZoneOffset.UTC));
        ObservationType type = new ObservationType((byte) 0);
        ObservationFormat format = new ObservationFormat((byte) 0);

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

        client.send(message.getRawBytes());
        client.close();

    }
}

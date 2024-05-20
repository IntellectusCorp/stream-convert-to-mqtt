import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

import data.*;
import kr.intellectus.biz.kma3.DateTime;
import kr.intellectus.biz.kma3.ObservationFormat;
import kr.intellectus.biz.kma3.ObservationMessage;
import kr.intellectus.biz.kma3.ObservationType;
import kr.intellectus.biz.kma3.metrics.Metrics;
import kr.intellectus.biz.kma3.metrics.MetricsGroup;

public class KMA3ObservationMessageBuilderTest {

        static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(KMA3ObservationMessageBuilderTest.class);

    @Test
    public void builderTest() throws Exception {

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

        logger.info(String.format("[writeWithBuilderTest] Message size(bytes): %d", message.getRawBytes().length));
    }

    @Test
    public void bytesBuilderTest() throws Exception {

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

        ObservationMessage targetMessage = ObservationMessage.builder().buildUsingBytes(message.getRawBytes());

        assertEquals(message.getObservationMetric(Metrics.AIR_PRESSURE).getObservedValueString(),
                targetMessage.getObservationMetric(Metrics.AIR_PRESSURE).getObservedValueString());

        assertEquals(message.getObservationMetric(Metrics.GRASS_TEMPERATURE).getObservedValueString(),
                targetMessage.getObservationMetric(Metrics.GRASS_TEMPERATURE).getObservedValueString());

    }
}

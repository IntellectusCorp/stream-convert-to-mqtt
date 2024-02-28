package kr.intellectus.infra;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import kr.intellectus.biz.kma3.ObservationMessage;
import kr.intellectus.biz.kma3.metrics.Metric;
import kr.intellectus.biz.kma3.metrics.Metrics;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.retry.RetryPolicy;
import software.amazon.awssdk.services.timestreamwrite.TimestreamWriteClient;
import software.amazon.awssdk.services.timestreamwrite.model.Dimension;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValue;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;
import software.amazon.awssdk.services.timestreamwrite.model.Record;
import software.amazon.awssdk.services.timestreamwrite.model.WriteRecordsRequest;

public class AWSTimestream {

        static final kr.intellectus.util.Logger logger = kr.intellectus.util.Loggers.getLogger(AWSTimestream.class);

        private final TimestreamWriteClient writeClient;

        private static final String DB_NAME = "iottelemetry-T4rBAJmCr3hw";
        private static final String Table_NAME = "js-integrated";
        private static final String REGION = "us-west-2";

        private static final String latitude = "37.4810971";
        private static final String longitude = "127.1143944";

        public static final AWSTimestream INSTANCE = new AWSTimestream();

        public AWSTimestream() {

                RetryPolicy retryPolicy = RetryPolicy.builder().build();
                ClientOverrideConfiguration.builder().apiCallAttemptTimeout(Duration.ofSeconds(10))
                                .retryPolicy(retryPolicy);
                TimestreamWriteClient client = TimestreamWriteClient.builder()
                                .build();
                this.writeClient = client;
        }

        public void writeRecordsWithUpsert(ObservationMessage message) {
                logger.info("AWSTimestream: Write records with upsert.");

                final long currentTMs = System.currentTimeMillis();
                final long version = System.currentTimeMillis();

                final List<Dimension> dimensions = new ArrayList<>();
                dimensions.add(Dimension.builder().name("region").value(REGION).build());
                dimensions.add(Dimension.builder().name("deviceId").value("demo-1").build());

                Dimension.builder().dimensionValueType("");

                Record commonAttributes = Record.builder()
                                .dimensions(dimensions)
                                .time(String.valueOf(currentTMs))
                                .version(version)
                                .build();

                List<MeasureValue> measureValues = new ArrayList<MeasureValue>();

                MeasureValue lati = MeasureValue.builder()
                                .name("latitude")
                                .value(latitude)
                                .type(MeasureValueType.DOUBLE)
                                .build();

                MeasureValue longi = MeasureValue.builder()
                                .name("longitude")
                                .value(longitude)
                                .type(MeasureValueType.DOUBLE)
                                .build();

                measureValues.add(lati);
                measureValues.add(longi);

                Iterator<Entry<Metrics, Metric>> iter = message.metricsIterator();
                while(iter.hasNext()) {
                        Metric metric = iter.next().getValue();

                        MeasureValue measure = MeasureValue.builder()
                                .name(metric.getMetricName())
                                .value(metric.getObservedValueString())
                                .type(metric.getDataTypeString())
                                .build();
                        
                        measureValues.add(measure);
                }


                // Metric metricAirPressure = message.getObservationMetric(Metrics.AIR_PRESSURE);
                // MeasureValue airPressure = MeasureValue.builder()
                //                 .name(metricAirPressure.getMetricName())
                //                 .value(metricAirPressure.getObservedValueString())
                //                 .type(MeasureValueType.DOUBLE)
                //                 .build();

                Record record = Record.builder()
                                .measureName("observed")
                                .measureValueType(MeasureValueType.MULTI)
                                // .measureValues(lati, longi, temperature, airPressure)
                                .measureValues(measureValues)
                                .build();

                WriteRecordsRequest writeRequest = WriteRecordsRequest.builder()
                                .databaseName(DB_NAME)
                                .tableName(Table_NAME)
                                .commonAttributes(commonAttributes)
                                .records(record)
                                .build();

                writeClient.writeRecords(writeRequest);
                logger.info("writeRecordsWithUpsert >  writing with " + message.printFullMessage());
        }

}

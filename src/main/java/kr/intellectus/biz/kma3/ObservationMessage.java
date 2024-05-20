package kr.intellectus.biz.kma3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.intellectus.biz.kma3.metrics.Metric;
import kr.intellectus.biz.kma3.metrics.Metrics;
import kr.intellectus.biz.kma3.metrics.MetricsGroup;
import kr.intellectus.biz.kma3.metrics.MetricsIndex;
import kr.intellectus.biz.kma3.metrics.ProtocolFlag;
import software.amazon.awssdk.thirdparty.jackson.core.util.ByteArrayBuilder;

public class ObservationMessage {

    static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ObservationMessage.class);

    private final byte[] messageRawBytes;

    private final ProtocolVersion protocolVersion;
    private final DateTime dateTime;
    private final ObservationType observationType;
    private final ObservationFormat observationFormat;

    private final Map<Metrics, Metric> metrics = new HashMap<Metrics, Metric>();

    private final  byte[] awsLocationNumber = new byte[2];
    private final byte[] crc16 = new byte[2];;

    private ObservationMessage(ProtocolVersion protocolVer, DateTime dateTime, ObservationType type,
            ObservationFormat format,
            Map<MetricsGroup, byte[]> observationMetrics) {

        this.protocolVersion = protocolVer;
        this.dateTime = dateTime;
        this.observationType = type;
        this.observationFormat = format;

        byte[] ABytes = observationMetrics.get(MetricsGroup.A);
        byte[] BBytes = observationMetrics.get(MetricsGroup.B);
        byte[] CBytes = observationMetrics.get(MetricsGroup.C);
        byte[] CLBytes = observationMetrics.get(MetricsGroup.CL);
        byte[] NBytes = observationMetrics.get(MetricsGroup.N);
        byte[] NLBytes = observationMetrics.get(MetricsGroup.NL);
        byte[] IBytes = observationMetrics.get(MetricsGroup.I);
        byte[] XBytes = observationMetrics.get(MetricsGroup.X);
        byte[] YBytes = observationMetrics.get(MetricsGroup.Y);

        // logger.info(String.format(
        //         "Constructing ObservationMessage with bytes size(A,B,C,CL,N,NL, I, X: %d, %d, %d, %d, %d, %d, %d, %d, %d)",
        //         ABytes.length, BBytes.length, CBytes.length, CLBytes.length, NBytes.length, NLBytes.length,
        //         IBytes.length, XBytes.length, YBytes.length));

        setObservationGroupMetrics(MetricsGroup.A, ABytes);
        setObservationGroupMetrics(MetricsGroup.B, BBytes);
        setObservationGroupMetrics(MetricsGroup.C, CBytes);
        setObservationGroupMetrics(MetricsGroup.CL, CLBytes);
        setObservationGroupMetrics(MetricsGroup.N, NBytes);
        setObservationGroupMetrics(MetricsGroup.NL, NLBytes);
        setObservationGroupMetrics(MetricsGroup.I, IBytes);
        setObservationGroupMetrics(MetricsGroup.X, XBytes);
        setObservationGroupMetrics(MetricsGroup.Y, YBytes);

        // extract raw bytes and merge to this.messageRawBytes
        ByteArrayBuilder bytesBuilder = new ByteArrayBuilder();

        // Ⅰ. 시작표시
        bytesBuilder.write(ProtocolFlag.BEGIN);

        // Ⅱ. 프로토콜 버전
        bytesBuilder.write(protocolVer.getRawBytes());

        // Ⅲ. 날짜/시간
        bytesBuilder.write(dateTime.getRawBytes());

        // Ⅳ. 자료구분
        bytesBuilder.write(observationType.getRawBytes());

        // Ⅴ. 자료형식번호
        bytesBuilder.write(observationFormat.getRawBytes());

        // Ⅵ. 지점번호
        bytesBuilder.write(awsLocationNumber);

        // Ⅶ. 자료내용
        bytesBuilder.write(ABytes);
        bytesBuilder.write(BBytes);
        bytesBuilder.write(CBytes);
        bytesBuilder.write(CLBytes);
        bytesBuilder.write(NBytes);
        bytesBuilder.write(NLBytes);
        bytesBuilder.write(IBytes);
        bytesBuilder.write(XBytes);
        bytesBuilder.write(YBytes);

        // Ⅷ. CRC CHECK
        bytesBuilder.write(crc16);

        // Ⅸ. 끝표시
        bytesBuilder.write(ProtocolFlag.END);

        this.messageRawBytes = new byte[bytesBuilder.size()];
        System.arraycopy(bytesBuilder.toByteArray(), 0, messageRawBytes, 0, bytesBuilder.size());
        bytesBuilder.close();
    }

    public byte[] getRawBytes() {
        return messageRawBytes;
    }

    public String getProtocolVersion() {
        return protocolVersion.printVersion();
    }

    public String getDataTime() {
        return dateTime.print();
    }

    public Metric getObservationMetric(Metrics metric) {
        return metrics.get(metric);
    }

    public Iterator<Entry<Metrics, Metric>> metricsIterator() {
        return metrics.entrySet().iterator();
    }

    private void setObservationGroupMetrics(MetricsGroup group, byte[] groupBytes) {

        ByteBuf buffer = Unpooled.wrappedBuffer(groupBytes);

        for (int i = 1; i < (group.NUM_OF_METRICS + 1); i++) {
            Metrics metric = Metrics.fromIndex(MetricsIndex.fromGroupAndNumber(group, i));

            try {
                int offsetBytes = (int) metric.CLASS.getField("OFFSET_BYTES").get(null);

                byte[] metricBytes = new byte[offsetBytes];
                buffer.readBytes(metricBytes);

                Metric metricInstance = (Metric) metric.CLASS.getConstructor(byte[].class).newInstance(metricBytes);
                metrics.put(metric, metricInstance);

            } catch (Exception exception) {

                exception.printStackTrace();
                logger.error("Error occurs during setObservationGroupMetrics.");

            }
        }
    }

    public String getObservationMetricsString() {
        StringBuilder builder = new StringBuilder();

        metrics.entrySet().stream().forEach(e -> {
            builder.append(e.getKey().TERM);
            builder.append(":");
            builder.append(e.getValue().getObservedValueString());
            builder.append("\n");
        });

        return builder.toString();
    }

    public String summary() {
        StringBuilder summary = new StringBuilder();

        summary.append(this.dateTime.print()); summary.append(", ");
        summary.append(this.metrics.get(Metrics.AIR_TEMPERATURE).getObservedValueString()); summary.append(", ");
        summary.append(this.metrics.get(Metrics.AIR_TEMPERATURE).getObservedValueString()); summary.append(", ");
        summary.append(this.metrics.get(Metrics.WIND_DIRECTION).getObservedValueString()); summary.append(", ");
        summary.append(this.metrics.get(Metrics.WIND_SPEED).getObservedValueString());

        return summary.toString();
    }

    public static Builder builder() {
        return new BuilderImpl();
    }

    public interface Builder {

        public ObservationMessage build() throws Exception;

        public ObservationMessage buildUsingBytes(byte[] messageBytes) throws Exception;

        public Builder setDateTime(DateTime dateTime);

        public Builder setObservationType(ObservationType type);

        public Builder setObservationFormat(ObservationFormat format);

        public Builder setOberservationGroupMetricsBytes(MetricsGroup group, byte[] groupBytes);

    }

    private static final class BuilderImpl implements Builder {

        // private final ObservationMessage instance;

        private final Map<MetricsGroup, byte[]> observationMetrics;

        private ProtocolVersion protocolVer;
        private DateTime dateTime;
        private ObservationType type;
        private ObservationFormat format;

        BuilderImpl() {
            this.protocolVer = null;
            this.observationMetrics = new HashMap<MetricsGroup, byte[]>();
        }

        @Override
        public Builder setOberservationGroupMetricsBytes(MetricsGroup group, byte[] groupBytes) {
            logger.info("To-do bytes size validation (given/offset) " + groupBytes.length + "/" + group.OFFSET_BYTES);
            if (groupBytes.length != group.OFFSET_BYTES) {
                logger.info(String.format("Error: expecting bytes length is %d, but now is %d", group.OFFSET_BYTES,
                        groupBytes.length));
            }
            observationMetrics.put(group, groupBytes);

            return this;
        }

        @Override
        public Builder setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;

            return this;
        }

        @Override
        public Builder setObservationType(ObservationType type) {
            this.type = type;

            return this;
        }

        @Override
        public Builder setObservationFormat(ObservationFormat format) {
            this.format = format;

            return this;
        }

        @Override
        public ObservationMessage build() throws Exception {
            if (dateTime == null) {
                throw new Exception("DateTime is required to create ObservationMessage.");
            }

            if (observationMetrics.containsKey(MetricsGroup.A) == false) {
                throw new Exception("MetricsGroup.A is required to create ObservationMessage.");
            }

            if (this.protocolVer == null) {
                this.protocolVer = new ProtocolVersion();
            }

            return new ObservationMessage(protocolVer, dateTime, type, format, observationMetrics);
        }

        @Override
        public ObservationMessage buildUsingBytes(final byte[] messageBytes) throws Exception {
    
            byte[] verBytes = new byte[ProtocolVersion.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, 2, verBytes, 0, verBytes.length);
            ProtocolVersion pVer = new ProtocolVersion(verBytes);

            byte[] datetimeBytes = new byte[DateTime.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, 5, datetimeBytes, 0, datetimeBytes.length);
            DateTime dt = new DateTime(datetimeBytes);

            byte[] typeBytes = new byte[ObservationType.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, 10, typeBytes, 0, typeBytes.length);
            ObservationType obsType = new ObservationType(typeBytes[0]);

            byte[] formatBytes = new byte[ObservationFormat.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, 11, formatBytes, 0, formatBytes.length);
            ObservationFormat obsFormat = new ObservationFormat(formatBytes[0]);

            int offset = 14;
            byte[] metricABytes = new byte[MetricsGroup.A.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, offset, metricABytes, 0, metricABytes.length);
            this.observationMetrics.put(MetricsGroup.A, metricABytes);

            offset += MetricsGroup.A.OFFSET_BYTES;

            byte[] metricBbytes = new byte[MetricsGroup.B.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, offset, metricBbytes, 0, metricBbytes.length);
            this.observationMetrics.put(MetricsGroup.B, metricBbytes);

            offset += MetricsGroup.B.OFFSET_BYTES;

            byte[] metricCbytes = new byte[MetricsGroup.C.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, offset, metricCbytes, 0, metricCbytes.length);
            this.observationMetrics.put(MetricsGroup.C, metricCbytes);

            offset += MetricsGroup.C.OFFSET_BYTES;

            byte[] metricCLbytes = new byte[MetricsGroup.CL.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, offset, metricCLbytes, 0, metricCLbytes.length);
            this.observationMetrics.put(MetricsGroup.CL, metricCLbytes);

            offset += MetricsGroup.CL.OFFSET_BYTES;

            byte[] metricNbytes = new byte[MetricsGroup.N.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, offset, metricNbytes, 0, metricNbytes.length);
            this.observationMetrics.put(MetricsGroup.N, metricNbytes);

            offset += MetricsGroup.N.OFFSET_BYTES;

            byte[] metricNLbytes = new byte[MetricsGroup.NL.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, offset, metricNLbytes, 0, metricNLbytes.length);
            this.observationMetrics.put(MetricsGroup.NL, metricNLbytes);

            offset += MetricsGroup.NL.OFFSET_BYTES;

            byte[] metricIbytes = new byte[MetricsGroup.I.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, offset, metricIbytes, 0, metricIbytes.length);
            this.observationMetrics.put(MetricsGroup.I, metricIbytes);

            offset += MetricsGroup.I.OFFSET_BYTES;

            byte[] metricXbytes = new byte[MetricsGroup.X.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, offset, metricXbytes, 0, metricXbytes.length);
            this.observationMetrics.put(MetricsGroup.X, metricXbytes);

            offset += MetricsGroup.X.OFFSET_BYTES;

            byte[] metricYbytes = new byte[MetricsGroup.Y.OFFSET_BYTES]; 
            System.arraycopy(messageBytes, offset, metricYbytes, 0, metricYbytes.length);
            this.observationMetrics.put(MetricsGroup.Y, metricYbytes);

            return new ObservationMessage(pVer, dt, obsType, obsFormat, this.observationMetrics);
        }
    }



    public String printFullMessage() {
        StringBuilder builder = new StringBuilder();

        builder.append(getProtocolVersion());
        builder.append("/");
        builder.append(getDataTime());
        builder.append("/");
        // builder.append(getAirTemperature().getObservedValueString());
        // builder.append("/");
        // builder.append(ObservationMeasureA.airPressure.getObservedValueString());

        return builder.toString();
    }

}

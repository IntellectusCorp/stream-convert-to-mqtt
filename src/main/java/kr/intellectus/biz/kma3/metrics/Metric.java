package kr.intellectus.biz.kma3.metrics;

public interface Metric {
    
    public byte[] getRawBytes();

    public String getMetricName();

    public int getRepresentedValue();

    public  String getObservedValueString();

    public String getDataTypeString();

    public int getNumberOfByteUsed();

}

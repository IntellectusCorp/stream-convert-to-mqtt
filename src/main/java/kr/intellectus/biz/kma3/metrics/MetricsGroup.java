package kr.intellectus.biz.kma3.metrics;

public enum MetricsGroup {
    A("A", 22, 11),
    B("B", 26, 13),
    C("C", 24, 12),
    CL("CL", 8, 4),
    N("N", 26, 13),
    NL("NL", 18, 9),
    I("I", 2, 1),
    X("X", 8, 1),
    Y("Y", 1, 1),
    UNKNOWN("UNKNOWN", 0, 0);

    private final String  gourp;
    public final int  OFFSET_BYTES;
    public final int  NUM_OF_METRICS;

    private MetricsGroup(final String group, final int offsetBytes, final int num) {
        this.gourp = group;
        this.OFFSET_BYTES = offsetBytes;
        this.NUM_OF_METRICS = num;
    }

    @Override
    public String toString() {
        return gourp;
    }
}

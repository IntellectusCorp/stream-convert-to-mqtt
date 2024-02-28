package kr.intellectus.biz.kma3.metrics;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum MetricsIndex {
    // Group A
    A1(MetricsGroup.A, 1),
    A2(MetricsGroup.A, 2),
    A3(MetricsGroup.A, 3),
    A4(MetricsGroup.A, 4),
    A5(MetricsGroup.A, 5),
    A6(MetricsGroup.A, 6),
    A7(MetricsGroup.A, 7),
    A8(MetricsGroup.A, 8),
    A9(MetricsGroup.A, 9),
    A10(MetricsGroup.A, 10),
    A11(MetricsGroup.A, 11),
    // Group B
    B1(MetricsGroup.B, 1),
    B2(MetricsGroup.B, 2),
    B3(MetricsGroup.B, 3),
    B4(MetricsGroup.B, 4),
    B5(MetricsGroup.B, 5),
    B6(MetricsGroup.B, 6),
    B7(MetricsGroup.B, 7),
    B8(MetricsGroup.B, 8),
    B9(MetricsGroup.B, 9),
    B10(MetricsGroup.B, 10),
    B11(MetricsGroup.B, 11),
    B12(MetricsGroup.B, 12),
    B13(MetricsGroup.B, 13),
    // Group C
    C1(MetricsGroup.C, 1),
    C2(MetricsGroup.C, 2),
    C3(MetricsGroup.C, 3),
    C4(MetricsGroup.C, 4),
    C5(MetricsGroup.C, 5),
    C6(MetricsGroup.C, 6),
    C7(MetricsGroup.C, 7),
    C8(MetricsGroup.C, 8),
    C9(MetricsGroup.C, 9),
    C10(MetricsGroup.C, 10),
    C11(MetricsGroup.C, 11),
    C12(MetricsGroup.C, 12),
    // Group CL
    CL1(MetricsGroup.CL, 1),
    CL2(MetricsGroup.CL, 2),
    CL3(MetricsGroup.CL, 3),
    CL4(MetricsGroup.CL, 4),
    // Group N
    N1(MetricsGroup.N, 1),
    N2(MetricsGroup.N, 2),
    N3(MetricsGroup.N, 3),
    N4(MetricsGroup.N, 4),
    N5(MetricsGroup.N, 5),
    N6(MetricsGroup.N, 6),
    N7(MetricsGroup.N, 7),
    N8(MetricsGroup.N, 8),
    N9(MetricsGroup.N, 9),
    N10(MetricsGroup.N, 10),
    N11(MetricsGroup.N, 11),
    N12(MetricsGroup.N, 12),
    N13(MetricsGroup.N, 13),
    // Group NL
    NL1(MetricsGroup.NL, 1),
    NL2(MetricsGroup.NL, 2),
    NL3(MetricsGroup.NL, 3),
    NL4(MetricsGroup.NL, 4),
    NL5(MetricsGroup.NL, 5),
    NL6(MetricsGroup.NL, 6),
    NL7(MetricsGroup.NL, 7),
    NL8(MetricsGroup.NL, 8),
    NL9(MetricsGroup.NL, 9),
    // Group I
    I1(MetricsGroup.I, 1),
    // Group X
    X1(MetricsGroup.X, 1),
    // Group Y
    Y1(MetricsGroup.Y, 1),
    // UNKNOWN
    UNKNOWN(MetricsGroup.C, 0);

    private final MetricsGroup metricsGroup;
    private final int number;

    private static final class InternalUtil {

        final Map<MetricsGroup, Map<Integer, MetricsIndex>> groupMap;

        InternalUtil() {
            groupMap = new HashMap<MetricsGroup, Map<Integer, MetricsIndex>>();
            Iterator<MetricsGroup> allGroup = EnumSet.allOf(MetricsGroup.class).iterator();
            while(allGroup.hasNext()) {
                MetricsGroup group = allGroup.next();
                groupMap.put(group, new HashMap<Integer, MetricsIndex>());
            }

            Iterator<MetricsIndex> allIndex = EnumSet.allOf(MetricsIndex.class).iterator();
            while (allIndex.hasNext()) {
                MetricsIndex index = allIndex.next();
                groupMap.get(index.metricsGroup).put(index.number, index);
            }
        }
    }

    private static final InternalUtil util = new InternalUtil();

    private MetricsIndex(final MetricsGroup group, final int num) {
        this.metricsGroup = group;
        this.number = num;
    }

    public static MetricsIndex fromGroupAndNumber(final MetricsGroup group, final int num) {
        return util.groupMap.get(group).get(num);
    }
}

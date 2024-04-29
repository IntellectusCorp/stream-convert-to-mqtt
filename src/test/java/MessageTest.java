import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import kr.intellectus.biz.kma3.metrics.Metric;
import kr.intellectus.biz.kma3.metrics.groupa.WindDirection;

public class MessageTest {
    
    @Test
    public void windDirectionTest() {

        byte[] rawBytes = new byte[2];
        rawBytes[0] = (byte) 0x04;
        rawBytes[1] = (byte) 0xB3;

        Metric windDirectionFromBytes = new WindDirection(rawBytes);
        assertEquals(1203, windDirectionFromBytes.getRepresentedValue());

        Metric windDirectionFromRepresented = new WindDirection(1203);
        Metric test = new WindDirection(windDirectionFromRepresented.getRawBytes());
        assertEquals(1203, test.getRepresentedValue());
    }
}

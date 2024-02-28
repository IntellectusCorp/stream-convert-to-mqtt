package metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import kr.intellectus.biz.kma3.metrics.Metric;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature500cm;
import kr.intellectus.biz.kma3.metrics.groupb.GrassTemperature;
import kr.intellectus.biz.kma3.metrics.groupb.GroundTemperature;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature100cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature10cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature150cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature20cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature300cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature30cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature50cm;
import kr.intellectus.biz.kma3.metrics.groupb.SoilTemperature5cm;
import kr.intellectus.biz.kma3.metrics.groupb.SolarIrradiance;
import kr.intellectus.biz.kma3.metrics.groupb.SunshineDuration;

public class MetricsGroupBTest {
    
    @Test
    public void observedValueFromRepresentedTest() {

        Metric solarIrradiance = new SolarIrradiance(32767);
        assertEquals(String.valueOf(327.67), solarIrradiance.getObservedValueString() );

        Metric sunshineDuration = new SunshineDuration(65535);
        assertEquals(String.valueOf(65535), sunshineDuration.getObservedValueString() );

        Metric groundTemperature = new GroundTemperature(2000);
        assertEquals(String.valueOf(100.0), groundTemperature.getObservedValueString() );

        Metric grassTemperature = new GrassTemperature(1999);
        assertEquals(String.valueOf(99.9), grassTemperature.getObservedValueString() );

        Metric soilTemp5 = new SoilTemperature5cm(1909);
        assertEquals(String.valueOf(90.9), soilTemp5.getObservedValueString() );

        Metric soilTemp10 = new SoilTemperature10cm(500);
        assertEquals(String.valueOf(-50.0), soilTemp10.getObservedValueString() );

        Metric soilTemp20 = new SoilTemperature20cm(510);
        assertEquals(String.valueOf(-49.0), soilTemp20.getObservedValueString() );

        Metric soilTemp30 = new SoilTemperature30cm(511);
        assertEquals(String.valueOf(-48.9), soilTemp30.getObservedValueString() );

        Metric soilTemp50 = new SoilTemperature50cm(1414);
        assertEquals(String.valueOf(41.4), soilTemp50.getObservedValueString() );

        Metric soilTemp100 = new SoilTemperature100cm(586);
        assertEquals(String.valueOf(-41.4), soilTemp100.getObservedValueString() );

        Metric soilTemp150 = new SoilTemperature150cm(1000);
        assertEquals(String.valueOf(0.0), soilTemp150.getObservedValueString() );

        Metric soilTemp300 = new SoilTemperature300cm(1001);
        assertEquals(String.valueOf(0.1), soilTemp300.getObservedValueString() );

        Metric soilTemp500 = new SoilTemperature500cm(999);
        assertEquals(String.valueOf(-0.1), soilTemp500.getObservedValueString() );

    }
}

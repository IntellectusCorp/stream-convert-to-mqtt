package metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import kr.intellectus.biz.kma3.metrics.Metric;
import kr.intellectus.biz.kma3.metrics.groupc.*;

public class MetricsGroupCTest {

    @Test
    public void observedValueFromRepresentedTest() {

        Metric cloudHeightLayer1 = new CloudHeightLayer1(8000);
        assertEquals(String.valueOf(8000), cloudHeightLayer1.getObservedValueString());

        Metric cloudHeightLayer2 = new CloudHeightLayer2(45678);
        assertEquals(String.valueOf(45678), cloudHeightLayer2.getObservedValueString());

        Metric cloudHeightLayer3 = new CloudHeightLayer3(1);
        assertEquals(String.valueOf(1), cloudHeightLayer3.getObservedValueString());

        Metric cloudAmount = new CloudAmount(10);
        assertEquals(String.valueOf(10), cloudAmount.getObservedValueString());

        Metric visibility = new Visibility(50000);
        assertEquals(String.valueOf(50000), visibility.getObservedValueString());

        Metric pm10 = new PM10(3599);
        assertEquals(String.valueOf(359.9), pm10.getObservedValueString());

        Metric pm25 = new PM25(1);
        assertEquals(String.valueOf(0.1), pm25.getObservedValueString());

        Metric radiationNet = new RadiationNet(10000);
        assertEquals(String.valueOf(0.0), radiationNet.getObservedValueString());

        Metric radiationTotalSky = new RadiationTotalSky(32767);
        assertEquals(String.valueOf(2276.7), radiationTotalSky.getObservedValueString());

        Metric radiationReflected = new RadiationReflected(2767);
        assertEquals(String.valueOf(-723.3), radiationReflected.getObservedValueString());

        Metric radiationDirectSolar = new RadiationDirectSolar(10001);
        assertEquals(String.valueOf(0.1), radiationDirectSolar.getObservedValueString());

        Metric currentWeatherConditions = new CurrentWeatherConditions(99);
        assertEquals(String.valueOf(99), currentWeatherConditions.getObservedValueString());

    }
}

package metrics;
import org.junit.jupiter.api.Test;

import kr.intellectus.biz.kma3.metrics.Measurement;
import kr.intellectus.biz.kma3.metrics.groupa.AirTemperature;

public class AirTemperatureTest {

    static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AirTemperatureTest.class);
    
    /*
     *  기압: 1분 평균 현지 기압
     *  사용비트 : 13 ～ 0 번 비트
     *  유효범위: 0 ~ 16383 (binary)
     *  표현범위: 5000 ~ 11000 (관측값 X 10)
     *  유효 관측값: 500 ~ 1100
     */
    @Test
    public void airTemperature() {

        Measurement representedAs2k = new AirTemperature(2000);
        byte[] result2k = representedAs2k.getRawBytes();
        logger.info(String.valueOf(representedAs2k.getRepresentedValue()));
        logger.info(String.valueOf(representedAs2k.getObservedValueString()));

        Measurement bytesExpected5k = new AirTemperature(result2k);
        logger.info(String.valueOf(bytesExpected5k.getRepresentedValue()));
        logger.info(String.valueOf(bytesExpected5k.getObservedValueString()));
    }
}

package kr.intellectus.biz.kma3.metrics;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import kr.intellectus.biz.kma3.metrics.groupa.*;
import kr.intellectus.biz.kma3.metrics.groupb.*;
import kr.intellectus.biz.kma3.metrics.groupc.*;
import kr.intellectus.biz.kma3.metrics.groupcl.*;
import kr.intellectus.biz.kma3.metrics.i.Tachometer;
import kr.intellectus.biz.kma3.metrics.n.*;
import kr.intellectus.biz.kma3.metrics.nl.*;
import kr.intellectus.biz.kma3.metrics.x.DeviceSensorStatus;
import kr.intellectus.biz.kma3.metrics.y.DeviceVoltageStatus;

public enum Metrics {
    // Group A
    AIR_TEMPERATURE("air_temperature", MetricsGroup.A, AirTemperature.class, MetricsIndex.A1),
    WIND_DIRECTION("wind_direction", MetricsGroup.A, WindDirection.class, MetricsIndex.A2),
    WIND_SPEED("wind_speed", MetricsGroup.A, WindSpeed.class, MetricsIndex.A3),
    WIND_DIRECTION_INSTANTANEOUS("wind_direction_inst", MetricsGroup.A, WindDirectionInstantaneous.class,
            MetricsIndex.A4),
    WIND_SPEED_INSTANTANEOUS("wind_speed_inst", MetricsGroup.A, WindSpeedInstantaneous.class, MetricsIndex.A5),
    PRECIPITATION0510("precipitation_05_10", MetricsGroup.A, Precipitation0510.class, MetricsIndex.A6),
    AIR_PRESSURE("air_pressure", MetricsGroup.A, AirPressure.class, MetricsIndex.A7),
    HAS_PRECIPITATION("has_precipitation", MetricsGroup.A, HasPrecipitation.class, MetricsIndex.A8),
    SNOWFALL_AMOUNT("snowfall_amount", MetricsGroup.A, SnowfallAmount.class, MetricsIndex.A9),
    RELATIVE_HUMIDITY("relative_humidity", MetricsGroup.A, RelativeHumidity.class, MetricsIndex.A10),
    PRECIPITATION01("precipitation01", MetricsGroup.A, Precipitation01.class, MetricsIndex.A11),
    // Group B
    SOLAR_IRRADIANCE("solar_irradiance", MetricsGroup.B, SolarIrradiance.class, MetricsIndex.B1),
    SUNSHINE_DURATION("sunshine_duration", MetricsGroup.B, SunshineDuration.class, MetricsIndex.B2),
    GROUND_TEMPERATURE("ground_temperature", MetricsGroup.B, GroundTemperature.class, MetricsIndex.B3),
    GRASS_TEMPERATURE("grass_temperature", MetricsGroup.B, GrassTemperature.class, MetricsIndex.B4),
    SOIL_TEMPERATURE_5CM("soil_temperature_5cm", MetricsGroup.B, SoilTemperature5cm.class, MetricsIndex.B5),
    SOIL_TEMPERATURE_10CM("soil_temperature_10cm", MetricsGroup.B, SoilTemperature10cm.class, MetricsIndex.B6),
    SOIL_TEMPERATURE_20CM("soil_temperature_20cm", MetricsGroup.B, SoilTemperature20cm.class, MetricsIndex.B7),
    SOIL_TEMPERATURE_30CM("soil_temperature_30cm", MetricsGroup.B, SoilTemperature30cm.class, MetricsIndex.B8),
    SOIL_TEMPERATURE_50CM("soil_temperature_50cm", MetricsGroup.B, SoilTemperature50cm.class, MetricsIndex.B9),
    SOIL_TEMPERATURE_100CM("soil_temperature_100cm", MetricsGroup.B, SoilTemperature100cm.class, MetricsIndex.B10),
    SOIL_TEMPERATURE_150CM("soil_temperature_150cm", MetricsGroup.B, SoilTemperature150cm.class, MetricsIndex.B11),
    SOIL_TEMPERATURE_300CM("soil_temperature_300cm", MetricsGroup.B, SoilTemperature300cm.class, MetricsIndex.B12),
    SOIL_TEMPERATURE_500CM("soil_temperature_500cm", MetricsGroup.B, SoilTemperature500cm.class, MetricsIndex.B13),
    // Group C
    CLOUD_HEIGHT_LAYER1("cloud_height_layer1", MetricsGroup.C, CloudHeightLayer1.class, MetricsIndex.C1),
    CLOUD_HEIGHT_LAYER2("cloud_height_layer2", MetricsGroup.C, CloudHeightLayer2.class, MetricsIndex.C2),
    CLOUD_HEIGHT_LAYER3("cloud_height_layer3", MetricsGroup.C, CloudHeightLayer3.class, MetricsIndex.C3),
    CLOUD_AMOUNT("cloud_amount", MetricsGroup.C, CloudAmount.class, MetricsIndex.C4),
    VISIBILITY("visibility", MetricsGroup.C, Visibility.class, MetricsIndex.C5),
    PM10("pm10", MetricsGroup.C, PM10.class, MetricsIndex.C6),
    PM25("pm25", MetricsGroup.C, PM25.class, MetricsIndex.C7),
    RADIATION_NET("radiation_net", MetricsGroup.C, RadiationNet.class, MetricsIndex.C8),
    RADIATION_TOTAL_SKY("radiation_total_sky", MetricsGroup.C, RadiationTotalSky.class, MetricsIndex.C9),
    RADIATION_REFLECTED("radiation_reflected", MetricsGroup.C, RadiationReflected.class, MetricsIndex.C10),
    RADIATION_DIRECT_SOLAR("radiation_direct_solar", MetricsGroup.C, RadiationDirectSolar.class, MetricsIndex.C11),
    CURRENT_WEATHER_CONDITIONS("current_weather_conditions", MetricsGroup.C, CurrentWeatherConditions.class,
            MetricsIndex.C12),
    // Group CL
    RESERVED_CL1("reserved_cl1", MetricsGroup.CL, ReservedCL1.class, MetricsIndex.CL1),
    RESERVED_CL2("reserved_cl2", MetricsGroup.CL, ReservedCL2.class, MetricsIndex.CL2),
    RESERVED_CL3("reserved_cl3", MetricsGroup.CL, ReservedCL3.class, MetricsIndex.CL3),
    RESERVED_CL4("reserved_cl4", MetricsGroup.CL, ReservedCL4.class, MetricsIndex.CL4),
    // Group N
    SOIL_MOISTURE10("soil_moisture_10", MetricsGroup.N, SoilMoisture10.class, MetricsIndex.N1),
    SOIL_MOISTURE20("soil_moisture_20", MetricsGroup.N, SoilMoisture20.class, MetricsIndex.N2),
    SOIL_MOISTURE30("soil_moisture_30", MetricsGroup.N, SoilMoisture30.class, MetricsIndex.N3),
    SOIL_MOISTURE50("soil_moisture_50", MetricsGroup.N, SoilMoisture50.class, MetricsIndex.N4),
    ILLUMINANCE("illuminance", MetricsGroup.N, Illuminance.class, MetricsIndex.N5),
    WIND_SPEED_150("wind_speed_150", MetricsGroup.N, WindSpeed150.class, MetricsIndex.N6),
    WIND_SPEED_400("wind_speed_400", MetricsGroup.N, WindSpeed400.class, MetricsIndex.N7),
    WIND_SPEED_INSTANTANEOUS_150("wind_speed_instantaneous_150", MetricsGroup.N, WindSpeedInstantaneous150.class, MetricsIndex.N8),
    WIND_SPEED_INSTANTANEOUS_400("wind_speed_instantaneous_400", MetricsGroup.N, WindSpeedInstantaneous400.class, MetricsIndex.N9),
    AIR_TEMPERATURE_50("air_temperature_50", MetricsGroup.N, AirTemperature50.class, MetricsIndex.N10),
    AIR_TEMPERATURE_400("air_temperature_400", MetricsGroup.N, AirTemperature400.class, MetricsIndex.N11),
    RELATIVE_HUMIDITY_50("relative_humidity_50", MetricsGroup.N, RelativeHumidity50.class, MetricsIndex.N12),
    RELATIVE_HUMIDITY_400("relative_humidity_400", MetricsGroup.N, RelativeHumidity400.class, MetricsIndex.N13),
    // Group NL
    RESERVED_NL1("reserved_nl1", MetricsGroup.NL, ReservedNL1.class, MetricsIndex.NL1),
    RESERVED_NL2("reserved_nl2", MetricsGroup.NL, ReservedNL2.class, MetricsIndex.NL2),
    RESERVED_NL3("reserved_nl3", MetricsGroup.NL, ReservedNL3.class, MetricsIndex.NL3),
    RESERVED_NL4("reserved_nl4", MetricsGroup.NL, ReservedNL4.class, MetricsIndex.NL4),
    RESERVED_NL5("reserved_nl5", MetricsGroup.NL, ReservedNL5.class, MetricsIndex.NL5),
    RESERVED_NL6("reserved_nl6", MetricsGroup.NL, ReservedNL6.class, MetricsIndex.NL6),
    RESERVED_NL7("reserved_nl7", MetricsGroup.NL, ReservedNL7.class, MetricsIndex.NL7),
    RESERVED_NL8("reserved_nl8", MetricsGroup.NL, ReservedNL8.class, MetricsIndex.NL8),
    RESERVED_NL9("reserved_nl9", MetricsGroup.NL, ReservedNL9.class, MetricsIndex.NL9),
    // Group I
    TACHOMETER("tachometer", MetricsGroup.I, Tachometer.class, MetricsIndex.I1),
    // Group X
    DEVICE_SENSOR_STATUS("device_sensor_status", MetricsGroup.X, DeviceSensorStatus.class, MetricsIndex.X1),
    // Group X
    DEVICE_VOLTAGE_STATUS("device_voltage_status", MetricsGroup.Y, DeviceVoltageStatus.class, MetricsIndex.Y1),
    // Unknown
    UNKNOWN("UNKNOWN", MetricsGroup.A, Metrics.class, MetricsIndex.UNKNOWN);

    public final String TERM;
    public final Class<?> CLASS;
    public final MetricsGroup group;

    private final MetricsIndex index;

    private static final Map<MetricsIndex, Metrics> VALUE_MAP = new HashMap<MetricsIndex, Metrics>();
    static {
        Iterator<Metrics> allValue = EnumSet.allOf(Metrics.class).iterator();
        while (allValue.hasNext()) {
            Metrics metric = allValue.next();
            VALUE_MAP.put(metric.index, metric);
        }
    }

    private Metrics(final String term, final MetricsGroup group, final Class<?> clazz, final MetricsIndex index) {
        this.TERM = term;
        this.group = group;
        this.index = index;
        this.CLASS = clazz;
    }

    @Override
    public String toString() {
        return TERM;
    }

    public String getTerm() {
        return TERM;
    }

    public MetricsGroup getGroup() {
        return group;
    }

    public MetricsIndex getIndex() {
        return index;
    }

    public static Metrics fromIndex(final MetricsIndex indexVal) {
        return VALUE_MAP.get(indexVal);
    }
}

/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.domain;

import java.math.BigDecimal;
import org.joda.time.LocalTime;

/**
 * Eniware day related datum.
 * 
 * @version 1.2
 */
public interface DayDatum extends Datum {

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} instantaneous
	 * sample key for {@link DayDatum#getSunrise()} values.
	 */
	static final String SUNRISE_KEY = "sunrise";

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} instantaneous
	 * sample key for {@link DayDatum#getSunset()} values.
	 */
	static final String SUNSET_KEY = "sunset";

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} instantaneous
	 * sample key for {@link DayDatum#getSunrise()} values.
	 * 
	 * @since 1.1
	 */
	static final String MOONRISE_KEY = "moonrise";

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} instantaneous
	 * sample key for {@link DayDatum#getSunset()} values.
	 * 
	 * @since 1.1
	 */
	static final String MOONSET_KEY = "moonset";

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} instantaneous
	 * sample key for {@link DayDatum#getTemperatureMaximum()} values.
	 * 
	 * @since 1.1
	 */
	static final String TEMPERATURE_MAXIMUM_KEY = "tempMax";

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} instantaneous
	 * sample key for {@link DayDatum#getTemperatureMinimum()} values.
	 * 
	 * @since 1.1
	 */
	static final String TEMPERATURE_MINIMUM_KEY = "tempMin";

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} status sample key
	 * for {@link DayDatum#getSkyConditions()} values.
	 */
	static final String SKY_CONDITIONS_KEY = "sky";

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} status sample key
	 * for {@link DayDatum#getBriefOverview()} values.
	 * 
	 * @since 1.2
	 */
	static final String BRIEF_OVERVIEW_KEY = "brief";

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} status sample key
	 * for {@link AtmosphericDatum#getWindSpeed()} values.
	 * 
	 * @since 1.2
	 */
	static final String WIND_SPEED_KEY = "wspeed";

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} status sample key
	 * for {@link AtmosphericDatum#getWindDirection()} values.
	 * 
	 * @since 1.2
	 */
	static final String WIND_DIRECTION_KEY = "wdir";

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} status sample key
	 * for {@link AtmosphericDatum#getRain()} values.
	 * 
	 * @since 1.2
	 */
	static final String RAIN_KEY = "rain";

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} status sample key
	 * for {@link AtmosphericDatum#getSnow()} values.
	 * 
	 * @since 1.2
	 */
	static final String SNOW_KEY = "snow";

	/**
	 * A tag for a forecast day sample, as opposed to an actual measurement.
	 * 
	 * @since 1.2
	 */
	static final String TAG_FORECAST = "forecast";

	/**
	 * Get the sunrise time.
	 * 
	 * @return the sunrise
	 */
	LocalTime getSunrise();

	/**
	 * Get the sunset time.
	 * 
	 * @return the sunset
	 */
	LocalTime getSunset();

	/**
	 * Get the sunrise time.
	 * 
	 * @return the moonrise
	 * @since 1.1
	 */
	LocalTime getMoonrise();

	/**
	 * Get the moonset time.
	 * 
	 * @return the moonset
	 * @since 1.1
	 */
	LocalTime getMoonset();

	/**
	 * Get the minimum temperature for the day.
	 * 
	 * @return The minimum temperature.
	 * @since 1.1
	 */
	BigDecimal getTemperatureMinimum();

	/**
	 * Get the maximum temperature for the day.
	 * 
	 * @return The maximum temperature.
	 * @since 1.1
	 */
	BigDecimal getTemperatureMaximum();

	/**
	 * Get a textual description of the sky conditions, e.g. "clear", "cloudy",
	 * etc.
	 * 
	 * @return general sky conditions
	 * @since 1.1
	 */
	String getSkyConditions();

	/**
	 * Get a brief textual description of the overall conditions, e.g. "Sunshine
	 * and some clouds. High 18C. Winds N at 10 to 15 km/h."
	 * 
	 * @return general overall conditions description
	 * @since 1.2
	 */
	String getBriefOverview();

	/**
	 * Get the wind speed, in meters / second.
	 * 
	 * @return wind speed
	 * @since 1.2
	 */
	BigDecimal getWindSpeed();

	/**
	 * Get the wind direction, in degrees.
	 * 
	 * @return wind direction
	 * @since 1.2
	 */
	Integer getWindDirection();

	/**
	 * Get the rain accumulation, in millimeters.
	 * 
	 * @return rain accumulation
	 * @since 1.2
	 */
	Integer getRain();

	/**
	 * Get the snow accumulation, in millimeters.
	 * 
	 * @return snow accumulation
	 * @since 1.2
	 */
	Integer getSnow();

}

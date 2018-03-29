/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.solarnetwork.util.SerializeIgnore;

/**
 * GeneralLocationDatum that also implements {@link AtmosphericDatum}.
 * 
 * @version 1.2
 */
public class GeneralAtmosphericDatum extends GeneralLocationDatum implements AtmosphericDatum {

	@Override
	@JsonIgnore
	@SerializeIgnore
	public BigDecimal getTemperature() {
		return getInstantaneousSampleBigDecimal(TEMPERATURE_KEY);
	}

	public void setTemperature(BigDecimal value) {
		putInstantaneousSampleValue(TEMPERATURE_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public BigDecimal getDewPoint() {
		return getInstantaneousSampleBigDecimal(DEW_POINT_KEY);
	}

	public void setDewPoint(BigDecimal value) {
		putInstantaneousSampleValue(DEW_POINT_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Integer getHumidity() {
		return getInstantaneousSampleInteger(HUMIDITY_KEY);
	}

	public void setHumidity(Integer value) {
		putInstantaneousSampleValue(HUMIDITY_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Integer getAtmosphericPressure() {
		return getInstantaneousSampleInteger(ATMOSPHERIC_PRESSURE_KEY);
	}

	public void setAtmosphericPressure(Integer value) {
		putInstantaneousSampleValue(ATMOSPHERIC_PRESSURE_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Integer getVisibility() {
		return getInstantaneousSampleInteger(VISIBILITY_KEY);
	}

	public void setVisibility(Integer value) {
		putInstantaneousSampleValue(VISIBILITY_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public String getSkyConditions() {
		return getStatusSampleString(SKY_CONDITIONS_KEY);
	}

	public void setSkyConditions(String value) {
		putStatusSampleValue(SKY_CONDITIONS_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public BigDecimal getWindSpeed() {
		return getInstantaneousSampleBigDecimal(WIND_SPEED_KEY);
	}

	public void setWindSpeed(BigDecimal value) {
		putInstantaneousSampleValue(WIND_SPEED_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Integer getWindDirection() {
		return getInstantaneousSampleInteger(WIND_DIRECTION_KEY);
	}

	public void setWindDirection(Integer value) {
		putInstantaneousSampleValue(WIND_DIRECTION_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Integer getRain() {
		return getInstantaneousSampleInteger(RAIN_KEY);
	}

	public void setRain(Integer value) {
		putInstantaneousSampleValue(RAIN_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Integer getSnow() {
		return getInstantaneousSampleInteger(SNOW_KEY);
	}

	public void setSnow(Integer value) {
		putInstantaneousSampleValue(SNOW_KEY, value);
	}

}

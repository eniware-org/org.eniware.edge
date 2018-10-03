/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import org.eniware.domain.EdgeControlInfo;
import org.eniware.domain.EdgeControlPropertyType;

/**
 * Implementation of {@link EdgeControlInfo} and {@link Datum}.
 * 
 * @version 1.1
 */
public class EdgeControlInfoDatum extends BaseDatum implements EdgeControlInfo {

	private EdgeControlPropertyType type;
	private String value;
	private Boolean readonly;
	private String unit;
	private String propertyName;

	@Override
	public String getControlId() {
		return getSourceId();
	}

	@Override
	public String getPropertyName() {
		return propertyName;
	}

	@Override
	public EdgeControlPropertyType getType() {
		return type;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public Boolean getReadonly() {
		return readonly;
	}

	@Override
	public String getUnit() {
		return unit;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.1
	 */
	@Override
	public Map<String, ?> getSampleData() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if ( type != null ) {
			map.put("type", type.toString());
		}
		if ( value != null ) {
			map.put("value", value);
		}
		if ( readonly != null ) {
			map.put("readonly", readonly);
		}
		if ( unit != null ) {
			map.put("unit", unit);
		}
		if ( propertyName != null ) {
			map.put("propertyName", propertyName);
		}
		return map;
	}

	@Override
	protected Map<String, Object> createSimpleMap() {
		Map<String, Object> map = super.createSimpleMap();
		if ( getSourceId() != null ) {
			map.put("controlId", getSourceId());
		}
		return map;
	}

	@Override
	public String toString() {
		return "EdgeControlInfoDatum{controlId=" + (getSourceId() == null ? "" : getSourceId())
				+ ",type=" + (type == null ? "" : type.toString()) + ",property="
				+ (propertyName == null ? "" : propertyName) + ",value=" + (value == null ? "" : value)
				+ '}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( !super.equals(obj) ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		EdgeControlInfoDatum other = (EdgeControlInfoDatum) obj;
		if ( propertyName == null ) {
			if ( other.propertyName != null ) {
				return false;
			}
		} else if ( !propertyName.equals(other.propertyName) ) {
			return false;
		}
		if ( unit == null ) {
			if ( other.unit != null ) {
				return false;
			}
		} else if ( !unit.equals(other.unit) ) {
			return false;
		}
		if ( value == null ) {
			if ( other.value != null ) {
				return false;
			}
		} else if ( !value.equals(other.value) ) {
			return false;
		}
		return true;
	}

	public void setType(EdgeControlPropertyType type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

}

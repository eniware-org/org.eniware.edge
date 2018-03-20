/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.domain;

/**
 * Base object for location datum, such as Price or Weather.
 * 
 * @author matt
 * @version 1.1
 * @deprecated use {@link GeneralLocationDatum}
 */
@Deprecated
public abstract class BaseLocationDatum extends BaseDatum {

	private Long locationId;

	/**
	 * Default constructor.
	 */
	public BaseLocationDatum() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
		result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
		return result;
	}

	/**
	 * Compare for equality.
	 * 
	 * <p>
	 * This method compares the {@code created} and {@code locationId} values
	 * for equality.
	 * </p>
	 * 
	 * @return <em>true</em> if the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		BaseLocationDatum other = (BaseLocationDatum) obj;
		if ( getCreated() == null ) {
			if ( other.getCreated() != null ) {
				return false;
			}
		} else if ( !getCreated().equals(other.getCreated()) ) {
			return false;
		}
		if ( locationId == null ) {
			if ( other.locationId != null ) {
				return false;
			}
		} else if ( !locationId.equals(other.locationId) ) {
			return false;
		}
		return true;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

}

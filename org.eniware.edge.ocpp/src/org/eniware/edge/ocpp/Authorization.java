/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.ocpp;

import java.util.Date;
import ocpp.v15.cs.AuthorizationStatus;
import ocpp.v15.cs.IdTagInfo;

/**
 * Extension of {@link IdTagInfo} to associate an ID tag value with the info
 * itself.
 * 
 * @version 1.0
 */
public class Authorization extends IdTagInfo {

	private Date created = null;
	private String idTag;

	/**
	 * Default constructor.
	 */
	public Authorization() {
		super();
	}

	/**
	 * Construct from an ID tag and associated info. This constructor copies the
	 * values out of the provided {@link IdTagInfo} into this object.
	 * 
	 * @param idTag
	 *        The ID tag value.
	 * @param info
	 *        The associated info.
	 */
	public Authorization(String idTag, IdTagInfo info) {
		super();
		setIdTag(idTag);
		if ( info != null ) {
			setExpiryDate(info.getExpiryDate());
			setParentIdTag(info.getParentIdTag());
			setStatus(info.getStatus());
		}
	}

	/**
	 * Test if this authorization is expired. An authorization is expired if it
	 * has an {@code expiryDate} and that {@code expiryDate} is not earlier than
	 * the current time.
	 * 
	 * @return Expired flag.
	 */
	public boolean isExpired() {
		return (expiryDate != null && expiryDate.toGregorianCalendar().getTimeInMillis() < System
				.currentTimeMillis());
	}

	/**
	 * Test if this authorization has an {@link AuthorizationStatus#ACCEPTED}
	 * state and is not exipred.
	 * 
	 * @return If accepted and not expired then <em>true</em>, otherwise
	 *         <em>false</em>.
	 */
	public boolean isAccepted() {
		return (AuthorizationStatus.ACCEPTED.equals(getStatus()) && !isExpired());
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getIdTag() {
		return idTag;
	}

	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}

}

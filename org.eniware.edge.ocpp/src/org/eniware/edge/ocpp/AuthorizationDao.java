/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp;

import java.util.Date;
import java.util.Map;
import ocpp.v15.cs.AuthorizationStatus;
import ocpp.v15.cs.IdTagInfo;

/**
 * DAO for a local authorization list.
 * 
 * @version 1.0
 */
public interface AuthorizationDao {

	/**
	 * Store (create or update) a {@link IdTagInfo} value associated with an ID
	 * tag.
	 * 
	 * @param auth
	 *        The {@link Authorization} to store.
	 */
	void storeAuthorization(Authorization auth);

	/**
	 * Get an {@link Authorization} for a given ID tag.
	 * 
	 * @param idTag
	 *        The ID of the info to get.
	 * @return The associated authorization, or <em>null</em> if not available.
	 */
	Authorization getAuthorization(String idTag);

	/**
	 * Delete all expired authorizations that expired on or before
	 * {@code olderThanDate}.
	 * 
	 * @param olderThanDate
	 *        The expiration date to delete, or <em>null</em> to use the current
	 *        time.
	 * @return The number of authorizations deleted.
	 */
	int deleteExpiredAuthorizations(Date olderThanDate);

	/**
	 * Get a Map of status values with corresponding counts representing the
	 * number of records in the database of that status.
	 * 
	 * @return Map of status counts, never <em>null</em>.
	 */
	Map<AuthorizationStatus, Integer> statusCounts();

}

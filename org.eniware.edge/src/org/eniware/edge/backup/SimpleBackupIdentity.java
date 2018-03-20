/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.util.Date;

/**
 * Basic implementation of {@link BackupIdentity}.
 * 
 * @author matt
 * @version 1.0
 */
public class SimpleBackupIdentity implements BackupIdentity {

	private final String key;
	private final Date date;
	private final Long nodeId;
	private final String qualifier;

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        the key
	 * @param date
	 *        the date
	 * @param nodeId
	 *        the node ID
	 * @param qualifier
	 *        the qualifier
	 */
	public SimpleBackupIdentity(String key, Date date, Long nodeId, String qualifier) {
		super();
		this.key = key;
		this.date = date;
		this.nodeId = nodeId;
		this.qualifier = qualifier;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public Long getNodeId() {
		return nodeId;
	}

	@Override
	public String getQualifier() {
		return qualifier;
	}

}

/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.util.Date;

/**
 * Simple implementation of {@link Backup}.
 * 
 * @version 1.2
 */
public class SimpleBackup implements Backup {

	private final Date date;
	private final String key;
	private final Long size;
	private final boolean complete;
	private final Long nodeId;
	private final String qualifier;

	/**
	 * Construct with values.
	 * 
	 * @param date
	 *        the date
	 * @param key
	 *        the key
	 * @param size
	 *        the size
	 * @param complete
	 *        the complete flag
	 */
	public SimpleBackup(Date date, String key, Long size, boolean complete) {
		this(null, date, key, size, complete);
	}

	/**
	 * Construct with values.
	 * 
	 * @param nodeId
	 *        the node ID
	 * @param date
	 *        the date
	 * @param key
	 *        the key
	 * @param size
	 *        the size
	 * @param complete
	 *        the complete flag
	 */
	public SimpleBackup(Long nodeId, Date date, String key, Long size, boolean complete) {
		this(nodeId, date, null, key, size, complete);
	}

	/**
	 * Construct with values.
	 * 
	 * @param nodeId
	 *        the node ID
	 * @param date
	 *        the date
	 * @param qualifier
	 *        the qualifier
	 * @param key
	 *        the key
	 * @param size
	 *        the size
	 * @param complete
	 *        the complete flag
	 * @since 1.2
	 */
	public SimpleBackup(Long nodeId, Date date, String qualifier, String key, Long size,
			boolean complete) {
		super();
		this.nodeId = nodeId;
		this.date = date;
		this.qualifier = qualifier;
		this.key = key;
		this.size = size;
		this.complete = complete;
	}

	/**
	 * Construct from an identity with values.
	 * 
	 * @param ident
	 *        the backup identity info
	 * @param size
	 *        the size
	 * @param complete
	 *        the complete flag
	 * @since 1.2
	 */
	public SimpleBackup(BackupIdentity ident, Long size, boolean complete) {
		this(ident.getNodeId(), ident.getDate(), ident.getQualifier(), ident.getKey(), size, complete);
	}

	@Override
	public Long getNodeId() {
		return nodeId;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getQualifier() {
		return qualifier;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Long getSize() {
		return size;
	}

	@Override
	public boolean isComplete() {
		return complete;
	}

}

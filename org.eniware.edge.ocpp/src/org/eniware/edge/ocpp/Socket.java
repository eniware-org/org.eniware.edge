/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp;

import java.util.Date;

/**
 * Domain object for a socket, which is a physical connector for charging a
 * device with.
 * 
 * @version 1.0
 */
public class Socket {

	private Date created;
	private String socketId;
	private boolean enabled = true;

	/**
	 * Default constructor.
	 */
	public Socket() {
		super();
	}

	/**
	 * Construct with values.
	 * 
	 * @param socketId
	 *        The socket ID to set.
	 * @param enabled
	 *        The enabled state to set.
	 */
	public Socket(String socketId, boolean enabled) {
		super();
		this.socketId = socketId;
		this.enabled = enabled;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getSocketId() {
		return socketId;
	}

	public void setSocketId(String socketId) {
		this.socketId = socketId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

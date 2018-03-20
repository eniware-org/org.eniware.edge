/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.ocpp;

import java.util.Date;
import ocpp.v15.cs.MeterValue;

/**
 * Representation of a meter reading value.
 * 
 * @author matt
 * @version 1.0
 */
public class ChargeSessionMeterReading extends MeterValue.Value {

	private Date ts;
	private String sessionId;

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}

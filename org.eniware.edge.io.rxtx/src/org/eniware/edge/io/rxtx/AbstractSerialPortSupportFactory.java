/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.rxtx;

import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import net.solarnetwork.node.support.SerialPortBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for factories for {@link SerialPortSupport} implementations.
 *
 * @version $Revision$ $Date$
 */
public abstract class AbstractSerialPortSupportFactory extends SerialPortBean {
	
	/** A class-level logger. */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	private String serialPort = "/dev/ttyUSB0";
	private long maxWait;
	
	/**
	 * Configure base properties on a {@link SerialPortSupport} instance.
	 * 
	 * @param obj the object to configure
	 */
	protected void setupSerialPortSupport(SerialPortSupport obj) {
		obj.setBaud(getBaud());
		obj.setDataBits(getDataBits());
		obj.setStopBits(getStopBits());
		obj.setParity(getParity());
		obj.setFlowControl(getFlowControl());
		obj.setReceiveFraming(getReceiveFraming());
		obj.setReceiveThreshold(getReceiveThreshold());
		obj.setReceiveTimeout(getReceiveTimeout());
		obj.setDtrFlag(getDtrFlag());
		obj.setRtsFlag(getRtsFlag());
	}
	
	/**
	 * Locate the {@link CommPortIdentifier} for the configured 
	 * {@link #getSerialPort()} value.
	 * 
	 * <p>This method will throw a RuntimeException if the port is not found.</p>
	 * 
	 * @return the CommPortIdentifier
	 */
	@SuppressWarnings("unchecked")
	protected CommPortIdentifier getCommPortIdentifier() {
		Enumeration<CommPortIdentifier> portIdentifiers = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier commPortId = null;
		List<String> foundNames = new ArrayList<String>(5);
		while ( portIdentifiers.hasMoreElements() ) {
			commPortId = portIdentifiers.nextElement();
			log.trace("Inspecting available port identifier: {}", commPortId);
			foundNames.add(commPortId.getName());
			if ( commPortId.getPortType() == CommPortIdentifier.PORT_SERIAL && 
					this.serialPort.equals(commPortId.getName()) ) {
				if ( log.isDebugEnabled() ) {
					log.debug("Found port identifier: {}", this.serialPort);
				}
				break;
			} 
		}
		if ( commPortId == null ) {
			throw new RuntimeException("Couldn't find port identifier for ["
					+this.serialPort +"]; available ports: " +foundNames);
		}
		return commPortId;
	}

	public long getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}
	public String getSerialPort() {
		return serialPort;
	}
	public void setSerialPort(String serialPort) {
		this.serialPort = serialPort;
	}
	
}
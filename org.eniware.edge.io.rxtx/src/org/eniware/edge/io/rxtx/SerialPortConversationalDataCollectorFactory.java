/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.io.rxtx;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import org.springframework.beans.factory.ObjectFactory;

/**
 * {@link ObjectFactory} for {@link SerialPortConversationalDataCollector} objects.
 * 
 * <p>Configure the properties of this class, then calls to {@link #getObject()} will
 * return new instances of {@link SerialPortConversationalDataCollector} for each
 * invocation, configured with this object's property values.</p>
 * 
 * @author matt
 * @version $Revision$ $Date$
 */
public class SerialPortConversationalDataCollectorFactory
extends AbstractSerialPortSupportFactory 
		implements ObjectFactory<SerialPortConversationalDataCollector> {

	private String commPortAppName = getClass().getName();
	private CommPortIdentifier portId = null;

	@Override
	public SerialPortConversationalDataCollector getObject() {
		if ( this.portId == null ) {
			this.portId = getCommPortIdentifier();
		}
		try {
			// establish the serial port connection
			SerialPort port = (SerialPort)portId.open(this.commPortAppName, 2000);
			SerialPortConversationalDataCollector obj = new SerialPortConversationalDataCollector(
					port, getMaxWait());
			setupSerialPortSupport(obj);
			return obj;
		} catch ( PortInUseException e ) {
			throw new RuntimeException(e);
		} catch ( IllegalArgumentException e ) {
			throw new RuntimeException(e);
		}
	}
	
}

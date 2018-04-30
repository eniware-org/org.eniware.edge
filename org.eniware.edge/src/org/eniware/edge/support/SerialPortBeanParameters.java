/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.support;

import java.util.List;

import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.support.BasicTextFieldSettingSpecifier;

/**
 * Parameters to configure a serial port with.
 * 
 * <p>
 * The configurable properties of this class are:
 * </p>
 * 
 * <dl class="class-properties">
 * <dt>serialPort</dt>
 * <dd>The name of the serial port to use. This is OS-specific, for example
 * <code>/dev/ttyUSB0</code>.</dd>
 * 
 * <dt>commPortAppName</dt>
 * <dd>A user-defined name to associate with the serial port. The serial port
 * can only be used by one application at a time.</dd>
 * 
 * <dt>maxWait</dt>
 * <dd>A maximum number of milliseconds to wait for the serial port to return
 * data, before giving up. This differs from the {@code receiveTimeout} 
 * setting in that this timeout is not set on the port itself, but is managed
 * by the application.</dd>
 * </dl>
 * 
 * @version $Revision$
 */
public class SerialPortBeanParameters extends SerialPortBean {

	private static final SerialPortBeanParameters DEFAULTS = new SerialPortBeanParameters();
	
	private String serialPort = "/dev/ttyUSB0";
	private String commPortAppName = "EniwareEdge";
	private long maxWait = 0;

	/**
	 * Get a list of setting specifiers for this bean.
	 * 
	 * @param prefix the bean prefix to use
	 * @return setting specifiers
	 */
	public static List<SettingSpecifier> getDefaultSettingSpecifiers(String prefix) {
		return getDefaultSettingSpecifiers(DEFAULTS, prefix);
	}

	/**
	 * Get a list of setting specifiers for this bean.
	 * 
	 * @param defaults the default values to use
	 * @param prefix the bean prefix to use
	 * @return setting specifiers
	 */
	public static List<SettingSpecifier> getDefaultSettingSpecifiers(
			SerialPortBeanParameters defaults, String prefix) {
		List<SettingSpecifier> results = SerialPortBean.getDefaultSettingSpecifiers(
				defaults, prefix);
		results.add(new BasicTextFieldSettingSpecifier(prefix + "commPortAppName", 
				defaults.getCommPortAppName()));
		results.add(new BasicTextFieldSettingSpecifier(prefix + "maxWait", 
				String.valueOf(defaults.getMaxWait())));
		return results;
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

	public String getCommPortAppName() {
		return commPortAppName;
	}

	public void setCommPortAppName(String commPortAppName) {
		this.commPortAppName = commPortAppName;
	}

}

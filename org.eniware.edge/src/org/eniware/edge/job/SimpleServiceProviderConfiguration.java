/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import java.util.Map;

/**
 * Basic configuration bean for a service provider.
 * 
 * @author matt
 * @version 1.0
 */
public class SimpleServiceProviderConfiguration implements ServiceProvider.ServiceConfiguration {

	private Object service;
	private String[] interfaces;
	private Map<String, Object> properties;

	@Override
	public Object getService() {
		return service;
	}

	public void setService(Object service) {
		this.service = service;
	}

	@Override
	public String[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(String[] interfaces) {
		this.interfaces = interfaces;
	}

	@Override
	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}

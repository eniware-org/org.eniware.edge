/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.osgi.framework.ServiceRegistration;

/**
 * A class to help with tracking registered services.
 * 
 * @param <T>
 *        the service object type
 * @author matt
 * @version 1.1
 */
public class RegisteredService<T> {

	private final T config;
	private final Map<String, ?> props;
	private List<ServiceRegistration<?>> regList;

	public RegisteredService(T config, Map<String, ?> properties) {
		if ( config == null || properties == null ) {
			throw new IllegalArgumentException("Config and properties must not be null");
		}
		this.config = config;
		this.props = properties;
	}

	public boolean isSameAs(T other, Map<String, ?> properties) {
		if ( other == null || properties == null ) {
			return false;
		}
		if ( !this.config.getClass().getName().equals(other.getClass().getName()) ) {
			return false;
		}
		if ( !areMapsSame(props, properties) ) {
			return false;
		}
		if ( !this.config.equals(other) ) {
			return false;
		}
		return true;
	}

	public static boolean areMapsSame(Map<String, ?> m1, Map<String, ?> m2) {
		if ( m1 == null && m2 == null ) {
			return true;
		}
		if ( m1 == null && m2 != null ) {
			return false;
		} else if ( m1 != null && m2 == null ) {
			return false;
		}
		if ( m1.size() != m2.size() ) {
			return false;
		}
		for ( Map.Entry<String, ?> me : m1.entrySet() ) {
			if ( !m2.containsKey(me.getKey()) ) {
				return false;
			}
			Object mine = me.getValue();
			Object other = m2.get(me.getKey());
			if ( mine == null && other != null ) {
				return false;
			} else if ( mine != null ) {
				if ( mine.getClass().isArray() ) {
					if ( !(other.getClass().isArray() && Arrays.deepEquals((Object[]) mine,
							(Object[]) other)) ) {
						return false;
					}
				} else if ( !mine.equals(other) ) {
					return false;
				}
			}
		}
		return true;
	}

	public void unregister() {
		if ( regList == null ) {
			return;
		}
		for ( ServiceRegistration<?> reg : regList ) {
			reg.unregister();
		}
	}

	public T getConfig() {
		return config;
	}

	public Map<String, ?> getProps() {
		return props;
	}

	/**
	 * Get the first registration.
	 * 
	 * @return the registration, or <em>null</em> if none available
	 */
	public ServiceRegistration<?> getReg() {
		return (regList == null || regList.size() < 1 ? null : regList.get(0));
	}

	/**
	 * Set a single service registration.
	 * 
	 * @param reg
	 *        the registration
	 */
	public void setReg(ServiceRegistration<?> reg) {
		if ( regList == null ) {
			regList = new ArrayList<ServiceRegistration<?>>(1);
		} else {
			regList.clear();
		}
		regList.add(reg);
	}

	/**
	 * Get the list of registrations.
	 * 
	 * @return the registrations, or <em>null</em> if none set
	 */
	public List<ServiceRegistration<?>> getRegList() {
		return regList;
	}

	/**
	 * Add a new registration.
	 * 
	 * @param reg
	 *        the registration
	 */
	public void addReg(ServiceRegistration<?> reg) {
		if ( regList == null ) {
			regList = new ArrayList<ServiceRegistration<?>>(1);
		}
		regList.add(reg);
	}
}

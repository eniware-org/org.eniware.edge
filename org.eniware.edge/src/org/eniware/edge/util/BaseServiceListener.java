/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.util;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract helper class for a service listener that registers new OSGi
 * services.
 * 
 * <p>
 * The configurable properties of this class are:
 * </p>
 * 
 * <dl class="class-properties">
 * <dt>bundleContext</dt>
 * <dd>The {@link BundleContext} to manage services with.</dd>
 * </dl>
 * 
 * @param <T>
 *        the service type
 * @param <R>
 *
 * @version 1.0
 */
public abstract class BaseServiceListener<T, R extends RegisteredService<T>> {

	private BundleContext bundleContext = null;

	private final List<R> registeredServices = new LinkedList<R>();

	/** A class-level logger. */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Register a new OSGi service.
	 * 
	 * @param tracked
	 *        the RegisteredService instance
	 * @param service
	 *        the service to register with OSGi
	 * @param serviceInterfaces
	 *        the interfaces to publish the OSGi service as
	 * @param serviceProps
	 *        properties for the OSGi service
	 */
	protected void addRegisteredService(R tracked, Object service, String[] serviceInterfaces,
			Dictionary<String, ?> serviceProps) {
		log.debug("Registering service [{}] with props {}", service, serviceProps);
		synchronized ( registeredServices ) {
			ServiceRegistration<?> reg = bundleContext.registerService(serviceInterfaces, service,
					serviceProps);
			tracked.setReg(reg);
			registeredServices.add(tracked);
		}
	}

	/**
	 * Track a collection of registered OSGi services.
	 * 
	 * @param tracked
	 *        the RegisteredService instance
	 * @param services
	 *        the service already registered with OSGi
	 */
	protected void addRegisteredService(R tracked, Collection<ServiceRegistration<?>> services) {
		synchronized ( registeredServices ) {
			for ( ServiceRegistration<?> reg : services ) {
				tracked.addReg(reg);
			}
			registeredServices.add(tracked);
		}
	}

	/**
	 * Remove the registered service associated with a specific service.
	 * 
	 * @param tracked
	 *        the tracked service to remove
	 * @param properties
	 *        the service properties
	 */
	protected void removeRegisteredService(T tracked, Map<String, ?> properties) {
		synchronized ( registeredServices ) {
			for ( Iterator<R> itr = registeredServices.iterator(); itr.hasNext(); ) {
				R regService = itr.next();
				if ( regService.isSameAs(tracked, properties) ) {
					log.debug("Unregistering service [{}] with props {}", tracked);
					regService.unregister();
					itr.remove();
					break;
				}
			}
		}
	}

	protected List<R> getRegisteredServices() {
		return registeredServices;
	}

	public BundleContext getBundleContext() {
		return bundleContext;
	}

	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

}

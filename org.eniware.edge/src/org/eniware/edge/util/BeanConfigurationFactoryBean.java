/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.util;

import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link FactoryBean} implementation that creates objects based on properties
 * specified on a {@link BeanConfiguration} object.
 * 
 * <p>The configurable properties of this class are:</p>
 * 
 * <dl class="class-properties">
 *   <dt>beanClass</dt>
 *   <dd>The type of object to create when {@link #getObject()} is called.</p>
 *   
 *   <dt>config</dt>
 *   <dd>The {@link BeanConfiguration} to use for configuring the created
 *   object instance. The {@link BeanConfiguration#getConfiguration()} will
 *   be used to configure properties of any created object.</dd>
 *   
 *   <dt>singleton</dt>
 *   <dd>If <em>true</em> (the default) then only ever create one object
 *   instance. Otherwise a new object instance will be created each time
 *   {@link #getObject()} is called.</dd>
 *   
 *   <dt>staticProperties</dt>
 *   <dd>An optional Map of additional properties to configure on the created
 *   object(s). These properties will be applied first if configured, followed
 *   by the {@link BeanConfiguration} properties, so these can serve as default
 *   values if needed.</dd>
 * </dl>
 * 
 * @author matt
 * @version $Id$
 */
public class BeanConfigurationFactoryBean<T> implements FactoryBean<T> {
	
	private BeanConfiguration config = null;
	private Class<T> beanClass = null;
	private boolean singleton = true;
	private Map<String, ?> staticProperties = null;
	
	private T singletonObject = null;
	
	private final Object monitor = new Object();

	public T getObject() throws Exception {
		if ( isSingleton() ) {
			synchronized ( monitor ) {
				if ( singletonObject == null ) {
					singletonObject = createObject();
				}
				return singletonObject;
			}
		}
		return createObject();
	}

	private T createObject() throws InstantiationException,
			IllegalAccessException {
		T obj = beanClass.newInstance();
		BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(obj);
		if ( this.staticProperties != null ) {
			wrapper.setPropertyValues(this.staticProperties);
		}
		wrapper.setPropertyValues(config.getConfiguration());
		return obj;
	}

	public Class<T> getObjectType() {
		return beanClass;
	}

	public boolean isSingleton() {
		return this.singleton;
	}

	/**
	 * @return the config
	 */
	public BeanConfiguration getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(BeanConfiguration config) {
		this.config = config;
	}

	/**
	 * @return the beanClass
	 */
	public Class<?> getBeanClass() {
		return beanClass;
	}

	/**
	 * @param beanClass the beanClass to set
	 */
	public void setBeanClass(Class<T> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * @param singleton the singleton to set
	 */
	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

	/**
	 * @return the staticProperties
	 */
	public Map<String, ?> getStaticProperties() {
		return staticProperties;
	}

	/**
	 * @param staticProperties the staticProperties to set
	 */
	public void setStaticProperties(Map<String, ?> staticProperties) {
		this.staticProperties = staticProperties;
	}
	
}

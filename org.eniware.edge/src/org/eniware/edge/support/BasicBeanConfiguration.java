/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.support;

import java.util.Map;

import org.eniware.edge.util.BeanConfiguration;

/**
 * Basic implementation of {@link BeanConfiguration}.
 * 
 * <p>The configurable properties of this class are:</p>
 * 
 * <dl class="class-properties">
 *   <dt>attributes</dt>
 *   <dd>A Map of attributes for the configuration.</dd>
 *   
 *   <dt>configuration</dt>
 *   <dd>A Map of bean property values.</dd>
 *   
 *   <dt>ordering</dt>
 *   <dd>An ordering value. Defaults to {@link #DEFAULT_ORDERING}.</dd>
 * </dl>
 * 
 * @version $Id$
 */
public class BasicBeanConfiguration implements BeanConfiguration {
	
	/** The default value for the {@code ordering} property. */
	public static final Integer DEFAULT_ORDERING = Integer.valueOf(0);
	
	private Map<String, Object> configuration;
	private Map<String, Object> attributes;
	private Integer ordering = DEFAULT_ORDERING;

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public Map<String, Object> getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Map<String, Object> configuration) {
		this.configuration = configuration;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the ordering
	 */
	public Integer getOrdering() {
		return ordering;
	}

	/**
	 * @param ordering the ordering to set
	 */
	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}

}

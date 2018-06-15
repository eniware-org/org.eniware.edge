/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ObjectFactory;

/**
 * Factory for String objects created from a format template.
 * 
 * <p>This method will call {@link String#format(String, Object...)} passing
 * in the {@code template} String and the configured {@code parameters}. If
 * any of the configured parameters are themselves {@link ObjectFactory}
 * instances, then {@link ObjectFactory#getObject()} will be passed as the 
 * parameter value rather than the {@code ObjectFactory} itself. This allows
 * dynamic data to be used as template parameters, for example the current
 * date could be used by way of the {@link CurrentDateObjectFactory}.</p>
 * 
 * <p>The configurable properties of this class are:</p>
 * 
 * <dl class="class-properties">
 *   <dt>template</dt>
 *   <dd>The base String template to use. This value will be passed to the
 *   {@link String#format(String, Object...)} method.</dd>
 *   
 *   <dt>parameters</dt>
 *   <dd>A list of parameters to pass to the 
 *   {@link String#format(String, Object...)} method. {@link ObjectFactory}
 *   objects are handled specially so that {@link ObjectFactory#getObject()}
 *   are passed as the parameter values instead of the {@code ObjectFactory}
 *   itself.</dd>
 * </dl>
 * 
 * @version $Id$
 */
public class StringFormatObjectFactory implements ObjectFactory<String> {

	private String template = null;
	private List<?> parameters = null;
	
	public String getObject() {
		List<Object> resolvedParameters = new ArrayList<Object>(parameters.size());
		for ( Object param : parameters ) {
			if ( param instanceof ObjectFactory<?> ) {
				ObjectFactory<?> factory = (ObjectFactory<?>)param;
				resolvedParameters.add(factory.getObject());
			} else {
				resolvedParameters.add(param);
			}
		}
		return String.format(template, resolvedParameters.toArray());
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the parameters
	 */
	public List<?> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(List<?> parameters) {
		this.parameters = parameters;
	}

}

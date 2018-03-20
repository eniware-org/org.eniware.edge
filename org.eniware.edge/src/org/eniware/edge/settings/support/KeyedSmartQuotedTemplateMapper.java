/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eniware.edge.settings.KeyedSettingSpecifier.Mapper;

/**
 * Dynamically maps property keys to support nested collections.
 * 
 * <p>
 * Only if the key passed to {@link #mapKey(String)} is a <em>simple</em>
 * property, it will be quoted. A simple property is one named with only
 * letters, numbers, and underscore characters.
 * </p>
 * 
 * <p>
 * The configurable properties of this class are:
 * </p>
 * 
 * <dl class="class-properties">
 * <dt>template</dt>
 * <dd>A format template that accepts a single parameter to be within the
 * template quote.</dd>
 * </dl>
 * 
 * @author matt
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class KeyedSmartQuotedTemplateMapper implements Mapper {

	private static final Pattern PROPERTY_PATTERN = Pattern.compile("([a-zA-Z0-9_]+)(.*)");

	private String template = "trigger.jobDataMap['%s']";

	@Override
	public String mapKey(String key) {
		String quoteParam;
		String suffix = "";
		Matcher m = PROPERTY_PATTERN.matcher(key);
		if ( m.matches() ) {
			quoteParam = m.group(1);
			suffix = m.group(2);
		} else {
			quoteParam = key;
		}
		return String.format(template, quoteParam) + suffix;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}

/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import java.io.IOException;
import java.util.Properties;

import org.eniware.edge.settings.CronExpressionSettingSpecifier;

/**
 * Basic implementation of {@link CronExpressionSettingSpecifier}.
 * 
 * @author matt
 * @version 1.0
 */
public class BasicCronExpressionSettingSpecifier extends BasicTextFieldSettingSpecifier
		implements CronExpressionSettingSpecifier {

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        the key
	 * @param defaultValue
	 *        the default value
	 */
	public BasicCronExpressionSettingSpecifier(String key, String defaultValue) {
		super(key, defaultValue);
		setDescriptionArguments(new Object[] { getCronSyntaxHelpLink() });
	}

	/**
	 * Get a URL to link to for help on cron expression syntax.
	 * 
	 * @return A URL to link to.
	 */
	public static final String getCronSyntaxHelpLink() {
		String result = "https://github.com/SolarNetwork/solarnetwork/wiki/SolarNode-Cron-Job-Syntax";
		Properties props = new Properties();
		try {
			props.load(org.eniware.edge.settings.support.BasicCronExpressionSettingSpecifier.class
					.getResourceAsStream("BasicCronExpressionSettingSpecifier.properties"));
			if ( props.containsKey("help.url") ) {
				result = props.getProperty("help.url");
			}
		} catch ( IOException e ) {
			// ignore this
		}
		return result;
	}

}

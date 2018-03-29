/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

/**
 * A read-write string setting.
 * 
 * @version 1.1
 */
public interface TextFieldSettingSpecifier extends TitleSettingSpecifier {

	/**
	 * Flag indicating the text should be hidden when editing.
	 * 
	 * @return <em>true</em> to hide the text
	 * @since 1.1
	 */
	boolean isSecureTextEntry();

}

/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Static JSP function methods.
 * 
 * @version $Revision$
 */
public final class TagFunctions {

	private TagFunctions() {
		// can't create me
	}

	/**
	 * Return <em>true</em> if {@code o} is an instance of the class {@code className}.
	 * 
	 * @param o the object to test
	 * @param className the class name to test
	 * @return boolean
	 */
	public static boolean instanceOf(Object o, String className) {
		if ( o == null || className == null ) {
			return false;
		}
		Class<?> clazz;
		try {
			clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
			return clazz.isInstance(o);
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	private static final Pattern JS_ESCAPE = Pattern.compile("(')");

	/**
	 * Create a single-quoted JavaScript string by escaping all single quotes
	 * with a backslash.
	 * 
	 * <p>Use this function inside a single-quoted JavaScript string literal to
	 * escape any single quotes within the string.
	 * 
	 * @param input the input string
	 * @return the escaped string
	 */
	public static String jsString(String input) {
		if ( input == null || input.length() < 1 ) {
			return "";
		}
		Matcher m = JS_ESCAPE.matcher(input);
		return m.replaceAll("\\\\$1");
	}
	
}

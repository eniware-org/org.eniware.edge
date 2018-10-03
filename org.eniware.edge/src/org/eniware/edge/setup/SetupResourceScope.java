/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

/**
 * A scope for setup resources.
 * 
 * Some resources should only be applied once across the entire application,
 * while others might need different scope.
 * 
 * @version 1.0
 * @since 1.49
 */
public enum SetupResourceScope {

	/** A default scope. */
	Default,

	/**
	 * Application wide scope, meaning the resource should be applied once at
	 * the entire application's level.
	 */
	Application;

}

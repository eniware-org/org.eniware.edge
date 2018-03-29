/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import static org.eniware.edge.setup.SetupResourceUtils.localeScore;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Basic implementation of {@link SetupResourceProvider} for serving static
 * content.
 * 
 * @version 1.0
 */
public class SimpleSetupResourceProvider implements SetupResourceProvider {

	private Locale defaultLocale = Locale.US;
	private List<SetupResource> resources;

	@Override
	public SetupResource getSetupResource(String resourceUID, Locale locale) {
		if ( resources == null ) {
			return null;
		}
		int bestScore = -1;
		SetupResource bestMatch = null;
		for ( SetupResource rsrc : resources ) {
			if ( resourceUID.equals(rsrc.getResourceUID()) ) {
				int score = localeScore(rsrc, locale, defaultLocale);
				if ( score == Integer.MAX_VALUE ) {
					return rsrc;
				}
				if ( bestMatch == null || score > bestScore ) {
					bestScore = score;
					bestMatch = rsrc;
				}
			}
		}
		return bestMatch;
	}

	@Override
	public Collection<SetupResource> getSetupResourcesForConsumer(String consumerType, Locale locale) {
		Collection<SetupResource> result;
		Map<String, SetupResource> bestMatches;
		if ( resources == null ) {
			result = Collections.emptyList();
		} else {
			bestMatches = new HashMap<String, SetupResource>();
			for ( SetupResource rsrc : resources ) {
				Set<String> supported = rsrc.getSupportedConsumerTypes();
				if ( supported == null || supported.contains(consumerType) ) {
					SetupResource currMatch = bestMatches.get(rsrc.getResourceUID());
					if ( localeScore(currMatch, locale, defaultLocale) < localeScore(rsrc, locale,
							defaultLocale) ) {
						bestMatches.put(rsrc.getResourceUID(), rsrc);
					}
				}
			}
			result = bestMatches.values();
		}

		return result;
	}

	public List<SetupResource> getResources() {
		return resources;
	}

	/**
	 * Set the list of resources to use.
	 * 
	 * @param resources
	 *        The fixed set of resources managed by this service.
	 */
	public void setResources(List<SetupResource> resources) {
		this.resources = resources;
	}

	/**
	 * Set the locale to use for resources that have no locale specified in
	 * their filename.
	 * 
	 * @param defaultLocale
	 *        The default locale.
	 */
	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

}

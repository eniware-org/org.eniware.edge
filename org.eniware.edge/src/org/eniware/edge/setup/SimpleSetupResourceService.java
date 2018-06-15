/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Basic implementation of {@link SetupResourceService}.
 * 
 * @version 1.0
 */
public class SimpleSetupResourceService implements SetupResourceService {

	private Collection<SetupResourceProvider> setupResourceProviders;

	@Override
	public SetupResource getSetupResource(String resourceUID, Locale locale) {
		for ( SetupResourceProvider provider : getSetupResourceProviders() ) {
			SetupResource r = provider.getSetupResource(resourceUID, locale);
			if ( r != null ) {
				return r;
			}
		}
		return null;
	}

	@Override
	public Collection<SetupResource> getSetupResourcesForConsumer(String consumerType, Locale locale) {
		List<SetupResource> result = new ArrayList<SetupResource>(8);
		for ( SetupResourceProvider provider : getSetupResourceProviders() ) {
			Collection<SetupResource> list = provider.getSetupResourcesForConsumer(consumerType, locale);
			if ( list != null ) {
				result.addAll(list);
			}
		}
		return result;
	}

	public Collection<SetupResourceProvider> getSetupResourceProviders() {
		return setupResourceProviders;
	}

	public void setSetupResourceProviders(Collection<SetupResourceProvider> setupResourceProviders) {
		this.setupResourceProviders = setupResourceProviders;
	}

}

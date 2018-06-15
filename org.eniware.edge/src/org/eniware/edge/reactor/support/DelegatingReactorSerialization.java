/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor.support;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eniware.edge.reactor.Instruction;
import org.eniware.edge.reactor.ReactorSerializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link ReactorSerializationService} that delegates to a collection of
 * other {@link ReactorSerializationService}, making use of the first one
 * that handles a given encode / decode request.
 * 
 * <p>In each method of the {@link ReactorSerializationService} API, this
 * class will iterate over each configured delegate in the {@code serializers}
 * property, calling the same method on each delegate. If the delegate does
 * not throw an {@link IllegalArgumentException}, this class stops iterating
 * and returns the delgate's result immediately.</p>
 * 
 * <p>The idea of this class is that many different Reactor IO services can
 * be registered at once within the system to support different encodings
 * all at the same time. This class is assumed to be published as a service
 * with a higher ranking than all other {@link ReactorSerializationService}
 * instances, so that consumers of the service use this one instead of any
 * individual service directly.</p>
 * 
 * <p>The configurable properties of this class are:</p>
 * 
 * <dl class="class-properties">
 *   <dt>serializers</dt>
 *   <dd>Collection of {@link ReactorSerializationService} to delegate to.</dd>
 * </dl>
 * 
 * @version $Revision$
 */
public class DelegatingReactorSerialization implements ReactorSerializationService {

	private Collection<ReactorSerializationService> serializers;

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public List<Instruction> decodeInstructions(String instructorId, Object in, String type,
			Map<String, ?> properties) {
		for ( ReactorSerializationService delegate : serializers ) {
			try {
				return delegate.decodeInstructions(instructorId, in, type, properties);
			} catch ( IllegalArgumentException e ) {
				if ( log.isDebugEnabled() ) {
					log.debug("ReactorSerializationService " +delegate 
							+" does not support decoding " +type);
				}
			}
		}
		return null;
	}

	@Override
	public Object encodeInstructions(Collection<Instruction> instructions, String type,
			Map<String, ?> properties) {
		for ( ReactorSerializationService delegate : serializers ) {
			try {
				return delegate.encodeInstructions(instructions, type, properties);
			} catch ( IllegalArgumentException e ) {
				if ( log.isDebugEnabled() ) {
					log.debug("ReactorSerializationService " +delegate 
							+" does not support encoding " +type);
				}
			}
		}
		return null;
	}

	public Collection<ReactorSerializationService> getSerializers() {
		return serializers;
	}
	public void setSerializers(Collection<ReactorSerializationService> serializers) {
		this.serializers = serializers;
	}
	
}

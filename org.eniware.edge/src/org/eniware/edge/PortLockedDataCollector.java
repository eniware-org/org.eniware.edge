/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

import java.util.concurrent.locks.Lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link DataCollector} that provides locking
 * functionality so that only one conversation at a time occurs for a given
 * serial port.
 * 
 * <p>The {@link Lock} passed to the constructor is assumed to be locked,
 * and the code using this class <b>must</b> call {@link #stopCollecting()}
 * to release the lock.</p>
 * 
 * @author matt
 * @version $Revision$
 */
public class PortLockedDataCollector implements DataCollector {

	private final DataCollector delegate;
	private final String port;
	private Lock lock;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * Construct using the default timeout values.
	 * 
	 * @param delegate the delegate
	 * @param port the port
	 * @param lock the lock (assumed to be locked already)
	 */
	public PortLockedDataCollector(DataCollector delegate, String port, Lock lock) {
		super();
		this.delegate = delegate;
		this.port = port;
		this.lock = lock;
	}

	public void collectData() {
		delegate.collectData();
	}

	public int bytesRead() {
		return delegate.bytesRead();
	}

	public byte[] getCollectedData() {
		return delegate.getCollectedData();
	}

	public String getCollectedDataAsString() {
		return delegate.getCollectedDataAsString();
	}

	public void stopCollecting() {
		try {
			delegate.stopCollecting();
		} finally {
			if ( lock != null ) {
				log.debug("Releasing port {} lock", port);
				lock.unlock();
				lock = null;
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if ( lock != null ) {
			lock.unlock();
			lock = null;
		}
		super.finalize();
	}

}

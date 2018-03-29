/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.dao;

import org.eniware.edge.dao.BatchableDao.BatchResult;

/**
 * Basic implementation of {@link BatchResult}.
 *
 * @version 1.0
 */
public class BasicBatchResult implements BatchResult {

	private int numProcessed = 0;
	
	public BasicBatchResult(int numProcessed) {
		super();
		this.numProcessed = numProcessed;
	}
	
	@Override
	public int numProcessed() {
		return numProcessed;
	}

}

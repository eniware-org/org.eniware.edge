/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.dao;

import java.util.Map;

import org.eniware.edge.dao.BatchableDao.BatchOptions;

/**
 * Basic implementation of {@link BatchOptions}.
 * 
 * @version 1.0
 */
public class BasicBatchOptions implements BatchOptions {
	
	public static final String DEFAULT_BATCH_NAME = "Anonymous";
	public static final int DEFAULT_BATCH_SIZE = 50;
	
	private String name;
	private boolean updatable = false;
	private int batchSize = DEFAULT_BATCH_SIZE;
	private Map<String, Object> parameters;

	/**
	 * Default constructor.
	 */
	public BasicBatchOptions() {
		this(DEFAULT_BATCH_NAME);
	}
	
	/**
	 * Construct with a name.
	 * 
	 * @param name the name
	 */
	public BasicBatchOptions(String name) {
		this(name, DEFAULT_BATCH_SIZE, false, null);
	}
	
	/**
	 * Construct with values.
	 * 
	 * @param name the name
	 * @param batchSize the size
	 * @param updatable updatable
	 * @param parameters the parameters
	 */
	public BasicBatchOptions(String name, int batchSize, boolean updatable, Map<String, Object> parameters) {
		super();
		this.name = name;
		this.batchSize = batchSize;
		this.updatable = updatable;
		this.parameters = parameters;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getBatchSize() {
		return batchSize;
	}

	@Override
	public boolean isUpdatable() {
		return updatable;
	}

	@Override
	public Map<String, Object> getParameters() {
		return parameters;
	}

}

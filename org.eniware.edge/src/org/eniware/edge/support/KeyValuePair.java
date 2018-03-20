/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.support;

import java.io.Serializable;

/**
 * A key and value pair.
 * 
 * @author matt
 * @version $Id$
 */
public class KeyValuePair implements Serializable, Comparable<KeyValuePair> {

	private static final long serialVersionUID = -8143671046909870551L;

	private String key;
	private String value;
	
	/**
	 * Default constructor.
	 */
	public KeyValuePair() {
		super();
	}

	/**
	 * Construct with values.
	 * 
	 * @param key the key
	 * @param value the value
	 */
	public KeyValuePair(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * Compare the {@code key} values of two KeyValuePair objects.
	 */
	public int compareTo(KeyValuePair o) {
		if ( key == null ) {
			return 1;
		}
		return key.compareTo(o.key);
	}
	
	@Override
	public String toString() {
		return "KeyValuePair{"+key+'='+value+'}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	/**
	 * Compare the {@code key} values of two KeyValuePair objects.
	 */
	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		KeyValuePair other = (KeyValuePair) obj;
		if ( key == null ) {
			if ( other.key != null ) {
				return false;
			}
		} else if ( !key.equals(other.key) ) {
			return false;
		}
		return true;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}

/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.util;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A non-blocking {@link Deque} with an enforced maximum number of elements.
 * 
 * <p>
 * Calls to {@link LimitedSizeDeque#addFirst(Object)} and
 * {@link #addLast(Object)} will remove elements to make room for the addition,
 * before adding the new element. The removal is done from the opposite size of
 * the deque.
 * </p>
 * 
 * @version 1.0
 * @since 1.51
 */
public class LimitedSizeDeque<E> extends ArrayDeque<E> implements Deque<E> {

	private static final long serialVersionUID = -2004653495520273734L;

	private final int maximumSize;

	/**
	 * Constructor.
	 * 
	 * @param maximumSize
	 *        the maximum number of elements allowed
	 */
	public LimitedSizeDeque(int maximumSize) {
		super();
		this.maximumSize = maximumSize;
	}

	@Override
	public void addFirst(E e) {
		while ( size() >= maximumSize ) {
			removeLast();
		}
		super.addFirst(e);
	}

	@Override
	public void addLast(E e) {
		while ( size() >= maximumSize ) {
			removeFirst();
		}
		super.addLast(e);
	}

}

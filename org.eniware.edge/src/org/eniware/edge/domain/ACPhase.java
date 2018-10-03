/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

/**
 * Enumeration of AC phase values.
 * 
 * @version 1.0
 */
public enum ACPhase {

	/** The first phase. */
	PhaseA(1),

	/** The second phase. */
	PhaseB(2),

	/** The third phase. */
	PhaseC(3),

	Total(0);

	private final int number;

	private ACPhase(int n) {
		this.number = n;
	}

	/**
	 * Get the integer based value of the phase. The {@code PhaseA},
	 * {@code PhaseB}, and {@code PhaseC} phases are numbered <em>1</em>,
	 * <em>2</em>, and <em>3</em>. The {{@code Total} phase is numbered
	 * <em>0</em>.
	 * 
	 * @return the phase number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Get an ACPhase for a given number.
	 * 
	 * @param n
	 *        the number
	 * @return the ACPhase
	 * @see #getNumber()
	 * @throws IllegalArgumentException
	 *         if the number is not a valid phase value
	 */
	public ACPhase forNumber(final int n) {
		switch (n) {
			case 0:
				return Total;

			case 1:
				return PhaseA;

			case 2:
				return PhaseB;

			case 3:
				return PhaseC;

			default:
				throw new IllegalArgumentException("Number " + n + " is not a valid ACPhase");
		}
	}

}

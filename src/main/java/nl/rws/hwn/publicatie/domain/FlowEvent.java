package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FlowEvent.
 */
public class FlowEvent extends DetectorEvent  implements Serializable {

	private static final long serialVersionUID = -8627578012504780748L;

	/** The count. */
	private Integer count;
	
	/** The notknown. */
	private Boolean notknown;

	/**
	 * Default constructor for JPA.
	 */
	public FlowEvent() {
		// EMPTY
	}

	/**
	 * Instantiates a new flow event.
	 *
	 * @param state the state
	 * @param count the count
	 * @param notknown the notknown
	 */
	public FlowEvent(Date state, Integer count, boolean notknown) {
		super(state);

		this.count = count;
		this.notknown = notknown;
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * Checks if is notknown.
	 *
	 * @return true, if is notknown
	 */
	public boolean isUnknown() {
		return notknown;
	}
}

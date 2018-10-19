package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class DetectorEvent.
 */
public abstract class DetectorEvent extends EventData  implements Serializable {

	private static final long serialVersionUID = -7505462392691867381L;

	/**
	 * Default constructor for JPA.
	 */
	public DetectorEvent() {
		// EMPTY
	}

	/**
	 * Instantiates a new detector event.
	 *
	 * @param state the state
	 */
	public DetectorEvent(Date state) {
		super(state);
	}


	@Override
	public String toString() {
		return super.toString() + "DetectorEvent";
	}
}

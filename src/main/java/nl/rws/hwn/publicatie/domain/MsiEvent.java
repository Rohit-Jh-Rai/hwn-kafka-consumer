package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class MsiEvent.
 */
public abstract class MsiEvent extends EventData  implements Serializable {

	private static final long serialVersionUID = 4106822133718318523L;

	/**
	 * Default constructor for JPA.
	 */
	public MsiEvent() {
		// EMPTY
	}

	/**
	 * Instantiates a new msi event.
	 *
	 * @param state the state
	 */
	public MsiEvent(Date state) {
		super(state);
	}

	@Override
	public String toString() {
		return super.toString() + " MsiEvent";
	}
}

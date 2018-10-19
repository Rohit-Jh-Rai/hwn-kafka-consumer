package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class DiscontinuedEvent.
 */
public class DiscontinuedEvent extends EventData  implements Serializable {

	private static final long serialVersionUID = -5862449175688818877L;

	/** Dummy field for workaround of Datanucleus bug. */
	private Boolean discontinued;

	/**
	 * Default constructor for JPA.
	 */
	public DiscontinuedEvent() {
		// EMPTY
	}

	/**
	 * Instantiates a new discontinued event.
	 *
	 * @param state the state
	 */
	public DiscontinuedEvent(Date state) {
		super(state);
		
		discontinued = true;
	}
	
	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public Boolean getDiscontinued() {
		return discontinued;
	}

	@Override
	public String toString() {
		return super.toString() + "DiscontinuedEvent:" +  discontinued;
	}
}

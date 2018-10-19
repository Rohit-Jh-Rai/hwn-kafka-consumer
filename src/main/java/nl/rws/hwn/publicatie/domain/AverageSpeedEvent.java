package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class AverageSpeedEvent.
 */
public class AverageSpeedEvent extends DetectorEvent  implements Serializable {

	private static final long serialVersionUID = -4489906121149303363L;

	/** The kmph. */
	private Integer kmph;
	
	/** The no traffic. */
	private Boolean noTraffic;
	
	/** The notknown. */
	private Boolean notknown;

	/**
	 * Default constructor for JPA.
	 */
	public AverageSpeedEvent() {
		// EMPTY
	}

	/**
	 * Instantiates a new average speed event.
	 *
	 * @param state the state
	 * @param kmph the kmph
	 * @param noTraffic the no traffic
	 * @param unknown the notknown
	 */
	public AverageSpeedEvent(Date state, Integer kmph, boolean noTraffic, boolean unknown) {
		super(state);
		this.kmph = kmph;
		this.noTraffic = noTraffic;
		this.notknown = unknown;
	}

	/**
	 * Gets the kmph.
	 *
	 * @return the kmph
	 */
	public Integer getKmph() {
		return kmph;
	}

	/**
	 * Checks if is no traffic.
	 *
	 * @return true, if is no traffic
	 */
	public boolean isNoTraffic() {
		return noTraffic;
	}

	/**
	 * Checks if is notknown.
	 *
	 * @return true, if is notknown
	 */
	public boolean getNotknown() {
		return notknown;
	}
}

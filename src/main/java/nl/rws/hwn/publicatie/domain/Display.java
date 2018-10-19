package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class Display.
 */
public class Display extends MsiEvent  implements Serializable {

	private static final long serialVersionUID = 5647602700929445454L;

	/**
	 * Default constructor for JPA.
	 */
	public Display() {
		// EMPTY
	}

	/**
	 * Instantiates a new display.
	 *
	 * @param state the state
	 * @param sign the sign
	 * @param speedlimit the speedlimit
	 * @param laneClosedAhead the lane closed ahead
	 * @param flashing the flashing
	 * @param redRing the red ring
	 */
	public Display(Date state, DisplaySign sign, Integer speedlimit, LaneClosedAhead laneClosedAhead, boolean flashing, boolean redRing) {
		super(state);

		this.sign = sign;
		this.speedlimit = speedlimit;
		this.laneClosedAhead = laneClosedAhead;
		this.redRing = redRing;
		this.flashing = flashing;
	}

	/**
	 * Instantiates a new display.
	 *
	 * @param state the state
	 * @param sign the sign
	 */
	public Display(Date state, DisplaySign sign) {
		this(state, sign, null, null, false, false);
	}

	/** The sign. */
	private DisplaySign sign;
	
	/** The speedlimit. */
	private Integer speedlimit;
	
	/** The lane closed ahead. */
	private LaneClosedAhead laneClosedAhead;
	
	/** The red ring. */
	private Boolean redRing;
	
	/** The flashing. */
	private Boolean flashing;

	/**
	 * Gets the sign.
	 *
	 * @return the sign
	 */
	public DisplaySign getSign() {
		return sign;
	}

	/**
	 * Gets the speedlimit.
	 *
	 * @return the speedlimit
	 */
	public Integer getSpeedlimit() {
		return speedlimit;
	}

	/**
	 * Gets the lane closed ahead.
	 *
	 * @return the lane closed ahead
	 */
	public LaneClosedAhead getLaneClosedAhead() {
		return laneClosedAhead;
	}
	
	/**
	 * Checks if is flashing.
	 *
	 * @return true, if is flashing
	 */
	public boolean isFlashing() {
		return flashing;
	}
	
	/**
	 * Checks if is red ring.
	 *
	 * @return true, if is red ring
	 */
	public boolean isRedRing() {
		return redRing;
	}
}

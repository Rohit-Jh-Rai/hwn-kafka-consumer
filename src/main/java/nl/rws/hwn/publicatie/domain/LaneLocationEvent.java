package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class LaneLocationEvent.
 */
public class LaneLocationEvent extends EventData  implements Serializable {

	private static final long serialVersionUID = 2277044630336755855L;

	/** The lane location. */
	private LaneLocation laneLocation;

	/**
	 * Default constructor for JPA.
	 */
	public LaneLocationEvent() {
		// EMPTY
	}

	/**
	 * Instantiates a new lane location event.
	 *
	 * @param state the state
	 * @param laneLocation the lane location
	 */
	public LaneLocationEvent(Date state, LaneLocation laneLocation) {
		super(state);

		this.laneLocation = laneLocation;
	}

	/**
	 * Gets the lane location.
	 *
	 * @return the lane location
	 */
	public LaneLocation getLaneLocation() {
		return laneLocation;
	}


	@Override
	public String toString() {
		return super.toString() + "LaneLocationEvent:"  +  laneLocation.toString();
	}
}

package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;

/**
 * The Class Detector.
 */
public class Detector extends PublicationObject  implements Serializable {

	private static final long serialVersionUID = -4337475434410315899L;

	/**
	 * Default constructor for JPA.
	 */
	public Detector() {
		// EMPTY
	}

	/**
	 * Instantiates a new detector.
	 *
	 * @param id the id
	 * @param laneLocation the lane location
	 */
	public Detector(String id, LaneLocation laneLocation) {
		super(id, laneLocation);
	}

	@Override
	public String toString() {
		return "Detector: " + this.getId() + this.getLaneLocation().toString();
	}
}

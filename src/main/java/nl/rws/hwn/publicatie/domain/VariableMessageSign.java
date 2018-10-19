package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;

/**
 * The Class VariableMessageSign.
 */
public class VariableMessageSign extends PublicationObject  implements Serializable {

	private static final long serialVersionUID = -4181503570835651018L;

	@Override
	public String toString() {
		return "VariableMessageSign:" + this.getId() + this.getLaneLocation().toString();
	}

	/**
	 * Default constructor for JPA.
	 */
	public VariableMessageSign() {
		// EMPTY
	}

	/**
	 * Instantiates a new variable message sign.
	 *
	 * @param id the id
	 * @param laneLocation the lane location
	 */
	public VariableMessageSign(String id, LaneLocation laneLocation) {
		super(id, laneLocation);
	}
}

package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Class RoadSection.
 */
public class Road  implements Serializable {

	private static final long serialVersionUID = -1757587701841602962L;

	/** The id. */
	private Integer id;

	/** The road letter. */
	private String roadLetter;
	
	/** The road number. */
	private int roadNumber;
	
	/**
	 * Default constructor for JPA.
	 */
	public Road() {
		// EMPTY
	}

	/**
	 * Instantiates a new road section.
	 *
	 * @param roadLetter the road letter
	 * @param roadNumber the road number
	 */
	public Road(String roadLetter, int roadNumber) {
		this.roadLetter = roadLetter;
		this.roadNumber = roadNumber;
	}

	/**
	 * Gets the road letter.
	 *
	 * @return the road letter
	 */
	public String getRoadLetter() {
		return roadLetter;
	}

	/**
	 * Gets the road number.
	 *
	 * @return the road number
	 */
	public int getRoadNumber() {
		return roadNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(roadLetter, roadNumber);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Road)) return false;
		if(this == obj) return true;

		Road o = (Road) obj;

		return Objects.equals(roadLetter, o.roadLetter) && Objects.equals(roadNumber, o.roadNumber);
	}
}

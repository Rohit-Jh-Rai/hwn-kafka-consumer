package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The Class LaneLocation.
 */

public class LaneLocation  implements Serializable {

	private static final long serialVersionUID = 7918693519214173531L;

	/** The road letter. */
	private String roadLetter;

	/** The road number. */
	private Integer roadNumber;

	/** The carriageway. */
	private String carriageway;

	/** The km. */
	private BigDecimal km;

	private Integer lane;

	/**
	 * Default constructor for JPA.
	 */
	public LaneLocation() {
		// EMPTY
	}

	/**
	 * Instantiates a new lane location.
	 *
	 * @param roadLetter the road letter
	 * @param roadNumber the road number
	 * @param carriageway the carriageway
	 * @param km the km
	 * @param lane the lane
	 */
	public LaneLocation(String roadLetter, int roadNumber, String carriageway, BigDecimal km, int lane) {
		this.roadLetter = roadLetter;
		this.roadNumber = roadNumber;
		this.carriageway = carriageway;
		this.km = km;
		this.lane = lane;
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
	 * Sets the road letter.
	 *
	 * @param roadLetter the new road letter
	 */
	public void setRoadLetter(String roadLetter) {
		this.roadLetter = roadLetter;
	}

	/**
	 * Gets the road number.
	 *
	 * @return the road number
	 */
	public int getRoadNumber() {
		return roadNumber.intValue();
	}

	/**
	 * Sets the road number.
	 *
	 * @param roadNumber the new road number
	 */
	public void setRoadNumber(int roadNumber) {
		this.roadNumber = roadNumber;
	}

	/**
	 * Gets the carriageway.
	 *
	 * @return the carriageway
	 */
	public String getCarriageway() {
		return carriageway;
	}

	/**
	 * Sets the carriageway.
	 *
	 * @param carriageway the new carriageway
	 */
	public void setCarriageway(String carriageway) {
		this.carriageway = carriageway;
	}

	/**
	 * Gets the km.
	 *
	 * @return the km
	 */
	public BigDecimal getKm() {
		return km;
	}

	/**
	 * Sets the km.
	 *
	 * @param km the new km
	 */
	public void setKm(BigDecimal km) {
		this.km = km;
	}

	/**
	 * Gets the lane.
	 *
	 * @return the lane
	 */
	public int getLane() {
		return lane;
	}

	/**
	 * Sets the lane.
	 *
	 * @param lane the new lane
	 */
	public void setLane(int lane) {
		this.lane = lane;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(roadLetter, roadNumber, carriageway, km, lane);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LaneLocation)) {
			return false;
		}

		LaneLocation other = (LaneLocation) obj;
		//@formatter:off
		return Objects.equals(roadLetter, other.roadLetter) && 
				Objects.equals(roadNumber, other.roadNumber) && 
				Objects.equals(carriageway, other.carriageway) && 
				Objects.equals(km, other.km) && 
				Objects.equals(lane, other.lane);
		//@formatter:on
	}

	@Override
	public String toString() {
		return 	roadLetter +roadNumber +"/" + carriageway + "/" +km + "/" + lane;
	}
}

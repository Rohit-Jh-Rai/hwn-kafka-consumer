package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Class PublicationObject.
 */
public abstract class PublicationObject  implements Serializable {

	private static final long serialVersionUID = -8632126992335020609L;

	/** The id. */
	private String id;

	/** The lane location. */
	private LaneLocation laneLocation;

	/** The configset. */
	private LokatieConfigsetReference configset;
	
	/** The discontinued. */
	private boolean discontinued;

	/**
	 * Default constructor for JPA.
	 */
	public PublicationObject() {
		// EMPTY
	}

	/**
	 * Instantiates a new publication object.
	 *
	 * @param id the id
	 * @param laneLocation the lane location
	 */
	public PublicationObject(String id, LaneLocation laneLocation) {		
		this.id = id;		
		this.laneLocation = laneLocation;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the lane location.
	 *
	 * @return the lane location
	 */
	public LaneLocation getLaneLocation() {
		return laneLocation;
	}

	/**
	 * Sets the lane location.
	 *
	 * @param laneLocation the new lane location
	 */
	public void setLaneLocation(LaneLocation laneLocation) {
		this.laneLocation = laneLocation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PublicationObject)) {
			return false;
		}

		PublicationObject other = (PublicationObject) obj;

		return Objects.equals(id, other.id);
	}

	/**
	 * Equal attr.
	 *
	 * @param incoming the incoming
	 * @return true, if successful
	 */
	public boolean equalAttr(PublicationObject incoming) {
		return Objects.equals(laneLocation, incoming.laneLocation) && Objects.equals(discontinued, incoming.discontinued);
	}
	
	/**
	 * Checks if is discontinued.
	 *
	 * @return true, if is discontinued
	 */
	public boolean isDiscontinued() {
		return discontinued;
	}
	
	/**
	 * Mark discontinued.
	 */
	public void markDiscontinued() {
		discontinued = true;
	}

	/**
	 * Mark continued.
	 */
	public void markContinued() {
		discontinued = false;
	}
	
	/**
	 * Sets the configset.
	 *
	 * @param configset the new configset
	 */
	public void setConfigset(LokatieConfigsetReference configset) {
		this.configset = configset;
	}
	
	/**
	 * Gets the configset.
	 *
	 * @return the configset
	 */
	public LokatieConfigsetReference getConfigset() {
		return configset;
	}

	/**
	 * Checks if is inconfigset.
	 *
	 * @param configset the configset
	 * @return true, if is inconfigset
	 */
	public boolean isInconfigset(LokatieConfigsetReference configset) {
		return this.configset.equals(configset);
	}
}

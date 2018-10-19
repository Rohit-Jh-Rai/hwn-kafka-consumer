package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class Event.
 */
public class Event  implements Serializable {

	private static final long serialVersionUID = -1017437940081343453L;

	/** The id. */
	private Integer id;

	/** The publication object. */
	private PublicationObject publicationObject;

	/** The event data. */
	private EventData eventData;
	
	/** The event. */
	private Timestamp event;

	/**
	 * Default constructor for JPA.
	 */
	public Event() {
		// EMPTY
	}

	/**
	 * Instantiates a new event.
	 *
	 * @param publicationObject the publication object
	 * @param eventData the event data
	 */
	public Event(PublicationObject publicationObject, EventData eventData) {
		this.publicationObject = publicationObject;
		this.eventData = eventData;		
	}

	/**
	 * Gets the publication object.
	 *
	 * @return the publication object
	 */
	public PublicationObject getPublicationObject() {
		return publicationObject;
	}

	/**
	 * Gets the event data.
	 *
	 * @return the event data
	 */
	public EventData getEventData() {
		return eventData;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public Timestamp getEvent() {
		return event;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public Date getState() {
		return eventData.getState();
	}

	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(Timestamp event) {
		this.event = event;
		
		this.eventData.setEvent(event);
	}
}

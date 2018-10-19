package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class EventData.
 */
public abstract class EventData  implements Serializable {

	private static final long serialVersionUID = -2573182732651104316L;

	/** The id. */
	private Integer id;

	/** The state. */
	private Date state;

	/** The ts_created_roadside. */
	private Date dateCreatedRoadside;

	/** The ts_received_dsb. */
	private Date dateReceivedDsb;

	/** The ts_ready_for_customer. */
	private Date dateReadyForCustomer;

	private Timestamp event;
	
	/**
	 * Default constructor for JPA.
	 */
	public EventData() {
		// EMPTY
	}

	/**
	 * Instantiates a new event data.
	 *
	 * @param state the state
	 */
	public EventData(Date state, Date dateCreatedRoadside, Date dateReceivedDsb, Date dateReadyForCustomer) {
		this.state = state;
		this.dateCreatedRoadside = dateCreatedRoadside;
		this.dateReceivedDsb = dateReceivedDsb;
		this.dateReadyForCustomer = dateReadyForCustomer;
	}

	public EventData(Date state) {
		this.state = state;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public Date getState() {
		return state;
	}

	public Date getDateCreatedRoadside() {
		return dateCreatedRoadside;
	}

	public void setDateCreatedRoadside(Date dateCreatedRoadside) {
		this.dateCreatedRoadside = dateCreatedRoadside;
	}

	public Date getDateReceivedDsb() {
		return dateReceivedDsb;
	}

	public void setDateReceivedDsb(Date dateReceivedDsb) {
		this.dateReceivedDsb = dateReceivedDsb;
	}

	public Date getDateReadyForCustomer() {
		return dateReadyForCustomer;
	}

	public void setDateReadyForCustomer(Date dateReadyForCustomer) {
		this.dateReadyForCustomer = dateReadyForCustomer;
	}

	public void setEvent(Timestamp event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return state.toString() + event.toString();
	}
}

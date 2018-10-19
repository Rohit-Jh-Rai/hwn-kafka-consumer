package nl.rws.hwn.publicatie.domain;

import java.util.Date;

/**
 * The Class TimeEventSelector.
 */
public class TimeEventSelector implements EventSelector {
	
	/** The event selector. */
	private EventSelector eventSelector;
	
	/** The start. */
	private Date start;
	
	/** The end. */
	private Date end;
	
	/**
	 * Instantiates a new time event selector.
	 *
	 * @param eventSelector the event selector
	 * @param start the start
	 * @param end the end
	 */
	public TimeEventSelector(EventSelector eventSelector, Date start, Date end) {
		this.eventSelector = eventSelector;
		this.start = start;
		this.end = end;
	}

	/* (non-Javadoc)
	 * @see nl.rws.hwn.publicatie.domain.EventSelector#matches(nl.rws.hwn.publicatie.domain.EventListener, nl.rws.hwn.publicatie.domain.Event)
	 */
	@Override
	public boolean matches(EventListener listener, Event event) {
		return eventSelector.matches(listener, event) && within(event.getEvent());
	}

	/* (non-Javadoc)
	 * @see nl.rws.hwn.publicatie.domain.EventSelector#valid(nl.rws.hwn.publicatie.domain.EventListener, nl.rws.hwn.publicatie.domain.Timestamp)
	 */
	@Override
	public boolean valid(EventListener listener, Timestamp now) {
		return eventSelector.valid(listener, now) && beforeEnd(now); 
	}
	
	/**
	 * Before end.
	 *
	 * @param timestamp the timestamp
	 * @return true, if successful
	 */
	public boolean beforeEnd(Timestamp timestamp) {
		return end.compareTo(timestamp.getDate()) > 0;
	}
	
	/**
	 * Within.
	 *
	 * @param timestamp the timestamp
	 * @return true, if successful
	 */
	private boolean within(Timestamp timestamp) {
		return timestamp.getDate().compareTo(start) >= 0 && timestamp.getDate().compareTo(end) < 0;
	}
}

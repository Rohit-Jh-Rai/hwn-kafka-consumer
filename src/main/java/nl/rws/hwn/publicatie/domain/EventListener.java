package nl.rws.hwn.publicatie.domain;

/**
 * The listener interface for receiving event events.
 * The class that is interested in processing a event
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addEventListener<code> method. When
 * the event event occurs, that object's appropriate
 * method is invoked.
 *
 * @see EventEvent
 */
public interface EventListener {
	
	/**
	 * Event.
	 *
	 * @param e the e
	 */
	public void event(Event e);

	/**
	 * Unregistered.
	 */
	public void unregistered();
}

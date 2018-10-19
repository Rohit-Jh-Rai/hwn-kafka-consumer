package nl.rws.hwn.publicatie.domain;

/**
 * The Interface EventSelector.
 */
public interface EventSelector {
	/**
	 * Indicates if the event should be delivered to the EventListener.
	 * 
	 * @param listener the listener that was used when registering
	 * @param event the incoming event
	 * @return true if should be delivered
	 */
	public boolean matches(EventListener listener, Event event);
	
	/**
	 * Indicates if a listener should receive further events.
	 * 
	 * <p>
	 * If it returns false then this listener will not be receiving events anymore and will it's resource will be cleared.
	 * </p>
	 * 
	 * @param listener the listener that was registered
	 * @param now the current date, events received after this invocation are guaranteed to have an older timestamp
	 * @return true if it is still valid
	 */
	public boolean valid(EventListener listener, Timestamp now);
}

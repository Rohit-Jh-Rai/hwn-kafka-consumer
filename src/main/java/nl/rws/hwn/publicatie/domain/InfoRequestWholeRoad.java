package nl.rws.hwn.publicatie.domain;


import java.util.Date;

/**
 * The Class InfoRequest.
 */
public class InfoRequestWholeRoad extends InfoRequest {
	
	/** The road letter. */
	private String roadLetter;
	
	/** The road number. */
	private int roadNumber;

	/**
	 * Instantiates a new info request.
	 *
	 * @param serviceType the service type
	 * @param start the start
	 * @param end the end
	 * @param roadLetter the road letter
	 * @param roadNumber the road number
	 * @param carriageway the carriageway
	 * @param startKm the start km
	 * @param endKm the end km
	 * @param consumer the consumer
	 */
	public InfoRequestWholeRoad(ServiceType serviceType, boolean compressOutput, Date start, Date end, String roadLetter, int roadNumber, String consumer) {
		super(serviceType, compressOutput, start, end, consumer);
		this.roadLetter = roadLetter;
		this.roadNumber = roadNumber;
	}

	/**
	 * Instantiates a new info request.
	 *
	 * @param serviceType the service type
	 * @param end the end
	 * @param roadLetter the road letter
	 * @param roadNumber the road number
	 * @param carriageway the carriageway
	 * @param startKm the start km
	 * @param endKm the end km
	 * @param consumer the consumer
	 */
	public InfoRequestWholeRoad(ServiceType serviceType, boolean compressOutput, Date end, String roadLetter, int roadNumber,
			String consumer) {
		super(serviceType, compressOutput, null, end, consumer);
		this.roadLetter = roadLetter;
		this.roadNumber = roadNumber;
	}

	/**
	 * Instantiates a new info request.
	 *
	 * @param serviceType the service type
	 * @param start the start
	 * @param end the end
	 * @param roadLetter the road letter
	 * @param roadNumber the road number
	 * @param carriageway the carriageway
	 * @param startKm the start km
	 * @param endKm the end km
	 * @param consumer the consumer
	 */
	public InfoRequestWholeRoad(String serviceType, boolean compressOutput, String start, String end, String roadLetter, int roadNumber, String consumer) {
		super(serviceType, compressOutput, start, end, consumer);
		this.roadLetter = roadLetter;
		this.roadNumber = roadNumber;
	}

	/**
	 * Instantiates a new info request.
	 *
	 * @param serviceType the service type
	 * @param end the end
	 * @param roadLetter the road letter
	 * @param roadNumber the road number
	 * @param carriageway the carriageway
	 * @param startKm the start km
	 * @param endKm the end km
	 * @param consumer the consumer
	 */
	public InfoRequestWholeRoad(String serviceType, boolean compressOutput, String end, String roadLetter, int roadNumber,
			String consumer) {
		super(serviceType, compressOutput, null, end, consumer);
		this.roadLetter = roadLetter;
		this.roadNumber = roadNumber;
	}

	/**
	 * Gets the road number.
	 *
	 * @return the road number
	 */
	public int getRoadNumber() {
		return roadNumber;
	}

	/**
	 * Gets the road letter.
	 *
	 * @return the road letter
	 */
	public String getRoadLetter() {
		return roadLetter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return consumer + "@" + XmlUtil.toSelection(roadLetter, roadNumber, start, end);
	}
}

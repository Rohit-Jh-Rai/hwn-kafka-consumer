package nl.rws.hwn.publicatie.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class InfoRequest.
 */
public class InfoRequestRoadSection extends InfoRequest {
	
	/** The road letter. */
	private String roadLetter;
	
	/** The road number. */
	private int roadNumber;
	
	/** The carriageway. */
	private String carriageway;
	
	/** The start km. */
	private BigDecimal startKm;
	
	/** The end km. */
	private BigDecimal endKm;
	
	
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
	public InfoRequestRoadSection(ServiceType serviceType, boolean compressOutput, Date start, Date end, String roadLetter, int roadNumber, String carriageway, BigDecimal startKm,
			BigDecimal endKm, String consumer) {
		super(serviceType, compressOutput, start, end, consumer);
		this.roadLetter = roadLetter;
		this.roadNumber = roadNumber;
		this.carriageway = carriageway;
		this.startKm = startKm;
		this.endKm = endKm;
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
	public InfoRequestRoadSection(ServiceType serviceType, boolean compressOutput, Date end, String roadLetter, int roadNumber, String carriageway, BigDecimal startKm, BigDecimal endKm,
			String consumer) {
		super(serviceType, compressOutput, null, end, consumer);
		this.roadLetter = roadLetter;
		this.roadNumber = roadNumber;
		this.carriageway = carriageway;
		this.startKm = startKm;
		this.endKm = endKm;
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
	public InfoRequestRoadSection(String serviceType, boolean compressOutput, String start, String end, String roadLetter, String roadNumber, String carriageway, String startKm,
			String endKm, String consumer) {
		super(serviceType, compressOutput, start, end, consumer);
		this.roadLetter = roadLetter;
		this.roadNumber = Integer.parseInt(roadNumber);
		this.carriageway = carriageway;
		this.startKm = new BigDecimal(startKm);
		this.endKm = new BigDecimal(endKm);
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
	public InfoRequestRoadSection(String serviceType, boolean compressOutput, String end, String roadLetter, String roadNumber, String carriageway, String startKm, String endKm,
			String consumer) {
		super(serviceType, compressOutput, null, end, consumer);
		this.roadLetter = roadLetter;
		this.roadNumber = Integer.parseInt(roadNumber);
		this.carriageway = carriageway;
		this.startKm = new BigDecimal(startKm);
		this.endKm = new BigDecimal(endKm);
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

	/**
	 * Gets the carriageway.
	 *
	 * @return the carriageway
	 */
	public String getCarriageway() {
		return carriageway;
	}

	/**
	 * Gets the start km.
	 *
	 * @return the start km
	 */
	public BigDecimal getStartKm() {
		return startKm;
	}

	/**
	 * Gets the end km.
	 *
	 * @return the end km
	 */
	public BigDecimal getEndKm() {
		return endKm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return consumer + "@" + XmlUtil.toSelection(roadLetter, roadNumber, carriageway, startKm, endKm, start, end);
	}
}

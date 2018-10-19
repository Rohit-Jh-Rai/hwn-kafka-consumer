package nl.rws.hwn.publicatie.domain;

import java.util.Date;

/**
 * The Class InfoRequest.
 */
public class InfoRequestHWN extends InfoRequest {
	
	/**
	 * Instantiates a new info request.
	 *
	 * @param serviceType the service type
	 * @param start the start date/time
	 * @param end the end date/time
	 * @param consumer the consumer
	 */
	public InfoRequestHWN(ServiceType serviceType, boolean compressOutput, Date start, Date end, String consumer) {
		super(serviceType, compressOutput, start, end, consumer);
	}

	/**
	 * Instantiates a new info request.
	 *
	 * @param serviceType the service type
	 * @param end the end
	 * @param consumer the consumer
	 */
	public InfoRequestHWN(ServiceType serviceType, boolean compressOutput, Date end, String consumer) {
		super(serviceType, compressOutput, null, end, consumer);
	}

	/**
	 * Instantiates a new info request.
	 *
	 * @param serviceType the service type
	 * @param start the start date/time
	 * @param end the end date/time
	 * @param consumer the consumer
	 */
	public InfoRequestHWN(String serviceType, boolean compressOutput, String start, String end, String consumer) {
		super(serviceType, compressOutput, start, end, consumer);
	}

	/**
	 * Instantiates a new info request.
	 *
	 * @param serviceType the service type
	 * @param end the end date/time
	 * @param consumer the consumer
	 */
	public InfoRequestHWN(String serviceType, boolean compressOutput, String end, String consumer) {
		super(serviceType, compressOutput,  null, end, consumer);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return consumer + "@" + XmlUtil.toSelection(start, end);
	}
}

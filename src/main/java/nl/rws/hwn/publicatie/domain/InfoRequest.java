package nl.rws.hwn.publicatie.domain;

import com.google.common.base.Strings;

import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public abstract class InfoRequest {

	/** The Constant UTC_TIMEZONE. */
	private static final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("GMT+00:00");
	/** The service type. */
	protected ServiceType serviceType;
	/** The start. */
	protected Date start;
	/** The end. */
	protected Date end;
	/** The consumer. */
	protected String consumer;
	/** The contract. */
	private Integer contract;
	/** true if the output should be compressed */
	private boolean compressedOutput;

	
	public InfoRequest(ServiceType serviceType, boolean compressOutput, Date start, Date end, String consumer) {
		super();
		this.serviceType = serviceType;
		this.compressedOutput = compressOutput;
		this.start = start;
		this.end = end;
		this.consumer = consumer;
	}

	public InfoRequest(String serviceType, boolean compressOutput, String start, String end, String consumer) {
		this(ServiceType.valueOf(serviceType), compressOutput, Strings.isNullOrEmpty(start) ? null : parseDate(start), parseDate(end), consumer);
	}

	
	/**
	 * Parses the date.
	 *
	 * @param date the date
	 * @return the date
	 */
	protected static Date parseDate(String date) {
		Calendar dateTime = DatatypeConverter.parseDateTime(date);
	
		if (!dateTime.getTimeZone().equals(UTC_TIMEZONE)) {
			throw new IllegalArgumentException("Date should be in specified timezone Z");
		}
	
		return dateTime.getTime();
	}

	public InfoRequest() {
		super();
	}

	/**
	 * Gets the service type.
	 *
	 * @return the service type
	 */
	public ServiceType getServiceType() {
		return serviceType;
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * Gets the end.
	 *
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * Gets the consumer.
	 *
	 * @return the consumer
	 */
	public String getConsumer() {
		return consumer;
	}

	/**
	 * Checks if is latest events.
	 *
	 * @return true, if is latest events
	 */
	public boolean isLatestEvents() {
		return start == null;
	}

	/**
	 * Gets the contract.
	 *
	 * @return the contract
	 */
	public Integer getContract() {
		return contract;
	}

	/**
	 * Sets the contract.
	 *
	 * @param contract the new contract
	 */
	public void setContract(Integer contract) {
		this.contract = contract;
	}

	/**
	 * Checks if is request in future.
	 *
	 * @param now the now
	 * @return true, if is request in future
	 */
	public boolean isRequestInFuture(Timestamp now) {
		return now.getDate().compareTo(start) < 0;
	}

	public boolean isCompressedOutput() {
		return compressedOutput;
	}

	public void setCompressedOutput(boolean compressedOutput) {
		this.compressedOutput = compressedOutput;
	}
}
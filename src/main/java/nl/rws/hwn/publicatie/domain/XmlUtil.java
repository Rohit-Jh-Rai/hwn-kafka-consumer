package nl.rws.hwn.publicatie.domain;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * The Class XmlUtil.
 */
public class XmlUtil {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	/** The Constant DATATYPE_FACTORY. */
	private static final DatatypeFactory DATATYPE_FACTORY;
	
	/** The Constant UTC_TIMEZONE. */
	private static final TimeZone UTC_TIMEZONE = SimpleTimeZone.getTimeZone("UTC");

	/** The Date format. */
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	static {
		try {
			DATATYPE_FACTORY = DatatypeFactory.newInstance();
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}
	
	/**
	 * Make date.
	 *
	 * @param date the date
	 * @return the XML gregorian calendar
	 */
	public static XMLGregorianCalendar makeDate(Date date) {
		GregorianCalendar c = new GregorianCalendar();

		c.setTime(date);
		c.setTimeZone(UTC_TIMEZONE);
		XMLGregorianCalendar time = DATATYPE_FACTORY.newXMLGregorianCalendar(c);
				
		return time;

	}

	/**
	 * Now.
	 *
	 * @return the XML gregorian calendar
	 */
	public static XMLGregorianCalendar now() {
		return makeDate(new Date());
	}

	/**
	 * To date.
	 *
	 * @param berichtAanmaak the bericht aanmaak
	 * @return the date
	 */
	public static Date toDate(XMLGregorianCalendar berichtAanmaak) {
		return berichtAanmaak.toGregorianCalendar().getTime();
	}
	
	public static String toSelection(InfoRequest infoRequest) {
		if(infoRequest instanceof InfoRequestRoadSection) {
			InfoRequestRoadSection ir = (InfoRequestRoadSection)infoRequest;
			return toSelection(ir.getRoadLetter(), ir.getRoadNumber(), ir.getCarriageway(), ir.getStartKm(), ir.getEndKm(), ir.getStart(), ir.getEnd());
		}
		if(infoRequest instanceof InfoRequestWholeRoad) {
			InfoRequestWholeRoad ir = (InfoRequestWholeRoad)infoRequest;
			return toSelection(ir.getRoadLetter(), ir.getRoadNumber(), ir.getStart(), ir.getEnd());
		}
		if(infoRequest instanceof InfoRequestHWN) {
			InfoRequestHWN ir = (InfoRequestHWN)infoRequest;
			return toSelection(ir.getStart(), ir.getEnd());
		}
		throw new RuntimeException("Unknown InforRequest subtype");
	}
	
	public static String toSelectionLatest(InfoRequest infoRequest) {
		if(infoRequest instanceof InfoRequestRoadSection) {
			InfoRequestRoadSection ir = (InfoRequestRoadSection)infoRequest;
			return toSelection(ir.getRoadLetter(), ir.getRoadNumber(), ir.getCarriageway(), ir.getStartKm(), ir.getEndKm(), null, ir.getEnd());
		}
		if(infoRequest instanceof InfoRequestWholeRoad) {
			InfoRequestWholeRoad ir = (InfoRequestWholeRoad)infoRequest;
			return toSelection(ir.getRoadLetter(), ir.getRoadNumber(), null, ir.getEnd());
		}
		if(infoRequest instanceof InfoRequestHWN) {
			InfoRequestHWN ir = (InfoRequestHWN)infoRequest;
			return toSelection(null, ir.getEnd());
		}
		throw new RuntimeException("Unknown InforRequest subtype");
	}
	
	/**
	 * To selection.
	 *
	 * @param roadLetter the road letter
	 * @param roadNumber the road number
	 * @param carriageway the carriageway
	 * @param kmStart the km start
	 * @param kmEnd the km end
	 * @param dtStart the dt start
	 * @param dtEnd the dt end
	 * @return the string
	 */
	public static String toSelection(String roadLetter, int roadNumber, String carriageway, BigDecimal kmStart, BigDecimal kmEnd, Date dtStart, Date dtEnd) {
		return "/" + StringUtils.join(new Object[] { roadLetter + roadNumber, carriageway, kmStart, kmEnd, dtStart != null ? XmlUtil.makeDate(dtStart) : "latest",
				XmlUtil.makeDate(dtEnd) }, '/');
	}
	
	public static String toSelection(String roadLetter, int roadNumber, Date dtStart, Date dtEnd) {
		return "/" + StringUtils.join(new Object[] { roadLetter + roadNumber, dtStart != null ? XmlUtil.makeDate(dtStart) : "latest",
				XmlUtil.makeDate(dtEnd) }, '/');
	}
	
	public static String toSelection(Date dtStart, Date dtEnd) {
		return "/" + StringUtils.join(new Object[] {dtStart != null ? XmlUtil.makeDate(dtStart) : "latest",
				XmlUtil.makeDate(dtEnd) }, '/');
	}

	/**
	 * convert timestamp String to Date
	 * @param ts String timestamp to convert
	 * @return converted Date
	 */
	public static Date convertTsDate(String ts) {
		Date date = null;
		if (ts == null || ts.isEmpty()) {
			logger.warn("convertTsDate received empty ts");
		} else {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			try {
				dateFormat.setTimeZone(UTC_TIMEZONE);
				date = dateFormat.parse(ts);
			} catch (ParseException ex) {
				logger.error("convertTsDate Date parse exception: " + ts);
			}
		}
		return date;
	}

	/**
	 * convert timestamp Date to String
	 * @param date Date timestamp to convert
	 * @return converted String
	 */
	public static String convertTsString(Date date) {
		String ts = null;
		if (date == null) {
			logger.warn("convertTsString received empty date");
		} else {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			dateFormat.setTimeZone(UTC_TIMEZONE);
			ts = dateFormat.format(date);
		}
		return ts;
	}

	/**
	 * get length dateformat
	 * @return length date format
	 */
	public static int getLengthDateFormat() {
		return DATE_FORMAT.length() - 4;
	}
}

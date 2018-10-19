package nl.rws.hwn.statistics.configuration;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * The Class XmlUtil.
 */
public class XmlUtil {

	/** The Constant DATATYPE_FACTORY. */
	private static final DatatypeFactory DATATYPE_FACTORY;

	/** The Constant UTC_TIMEZONE. */
	private static final TimeZone UTC_TIMEZONE = SimpleTimeZone.getTimeZone("UTC");

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
	 * @param date
	 *            the date
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

	public static XMLGregorianCalendar add(XMLGregorianCalendar date, long milliseconds) {
		long newtimeInMillis = date.toGregorianCalendar().getTimeInMillis() + milliseconds;

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(newtimeInMillis);
		XMLGregorianCalendar newXMLGregorianCalendar = DATATYPE_FACTORY.newXMLGregorianCalendar(calendar);
		return newXMLGregorianCalendar;
	}

	/**
	 * To date.
	 *
	 * @param berichtAanmaak
	 *            the bericht aanmaak
	 * @return the date
	 */
	public static Date toDate(XMLGregorianCalendar berichtAanmaak) {
		return berichtAanmaak.toGregorianCalendar().getTime();
	}

}

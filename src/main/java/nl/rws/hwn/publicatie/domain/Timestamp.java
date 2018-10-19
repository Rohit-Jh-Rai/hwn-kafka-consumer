package nl.rws.hwn.publicatie.domain;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Date;

/**
 * Representation of a timestamp.
 * 
 */
public class Timestamp implements Comparable<Timestamp>, Serializable {

	private static final long serialVersionUID = 7036876102486420752L;

	/** The date. */
	private Date date; // TODO long

	/** The sequence. */
	private int sequence;

	/**
	 * Instantiates a new timestamp.
	 */
	public Timestamp() {
	}
	
	/**
	 * Instantiates a new timestamp.
	 *
	 * @param copy the copy
	 */
	public Timestamp(Timestamp copy) {
		this.date = (Date) copy.date.clone();
		this.sequence = copy.sequence;
	}

	/**
	 * Instantiates a new timestamp.
	 *
	 * @param date the date
	 * @param sequence the sequence
	 */
	public Timestamp(Date date, int sequence) {
		this.date = date;
		this.sequence = sequence;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets the sequence.
	 *
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Timestamp paramT) {
		int compare = date.compareTo(paramT.date);

		if (compare == 0) {
			return sequence < paramT.sequence ? -1 : ((sequence == paramT.sequence) ? 0 : 1);
		}

		return compare;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return date.hashCode() + sequence;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Timestamp)) {
			return false;
		}

		Timestamp other = (Timestamp) obj;

		return other.date.equals(date) && other.sequence == sequence;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("date", date.getTime()).add("sequence", sequence).toString();
	}

	/**
	 * After.
	 *
	 * @param other the other
	 * @return true, if successful
	 */
	public boolean after(Date other) {
		return date.compareTo(other) > 0;
	}
}

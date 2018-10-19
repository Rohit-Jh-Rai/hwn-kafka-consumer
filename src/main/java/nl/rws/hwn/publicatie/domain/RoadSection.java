package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The Class RoadSection.
 */
public class RoadSection implements Serializable {

    private static final long serialVersionUID = -6022349787241654028L;

    /**
     * The id.
     */
    private Integer id;

    /**
     * The road letter.
     */
    private String roadLetter;

    /**
     * The road number.
     */
    private int roadNumber;

    /**
     * The carriageway.
     */
    private String carriageway;

    /**
     * The start km.
     */
    private BigDecimal startKm;

    /**
     * The end km.
     */
    private BigDecimal endKm;

    /**
     * Default constructor for JPA.
     */
    public RoadSection() {
        // EMPTY
    }

    /**
     * Instantiates a new road section.
     *
     * @param roadLetter  the road letter
     * @param roadNumber  the road number
     * @param carriageway the carriageway
     * @param startKm     the start km
     * @param endKm       the end km
     */
    public RoadSection(String roadLetter, int roadNumber, String carriageway, BigDecimal startKm, BigDecimal endKm) {
        this.roadLetter = roadLetter;
        this.roadNumber = roadNumber;
        this.carriageway = carriageway;
        this.startKm = startKm;
        this.endKm = endKm;
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
     * Gets the road number.
     *
     * @return the road number
     */
    public int getRoadNumber() {
        return roadNumber;
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
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(roadLetter, roadNumber, carriageway, startKm, endKm);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RoadSection)) {
            return false;
        }

        RoadSection o = (RoadSection) obj;

        return Objects.equals(roadLetter, o.roadLetter) && Objects.equals(roadNumber, o.roadNumber) && Objects.equals(carriageway, o.carriageway)
                && Objects.equals(startKm, o.startKm) && Objects.equals(endKm, o.endKm);
    }
}

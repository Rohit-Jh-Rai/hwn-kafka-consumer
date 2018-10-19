package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class VehicleDetectionEvent.
 */
public class VehicleDetectionEvent extends DetectorEvent  implements Serializable {

    private static final long serialVersionUID = -291739250882433110L;

    /** The velocity. */
	private BigDecimal velocity;
    
    /** The velocity unknown. */
    private Boolean velocityUnknown;
	
	/** The length. */
	private BigDecimal length;
    
    /** The length unknown. */
    private Boolean lengthUnknown;

    /**
     * Default constructor for JPA.
     */
    public VehicleDetectionEvent() {
        // EMPTY
    }

    /**
	 * Instantiates a new vehicle detection event.
	 *
	 * @param state the state
	 * @param velocity the velocity
	 * @param velocityUnknown the velocity unknown
	 * @param length the length
	 * @param lengthUnknown the length unknown
	 */
	public VehicleDetectionEvent(Date state, BigDecimal velocity, Boolean velocityUnknown, BigDecimal length, Boolean lengthUnknown) {
		super(state);

		this.velocity = velocity;
        this.velocityUnknown = velocityUnknown;
		this.length = length;
        this.lengthUnknown = lengthUnknown;
	}

    /**
     * Gets the velocity.
     *
     * @return the velocity
     */
    public BigDecimal getVelocity() {
        return velocity;
    }

    /**
     * Gets the velocity unknown.
     *
     * @return the velocity unknown
     */
    public Boolean getVelocityUnknown() {
        return velocityUnknown;
    }

    /**
     * Gets the length.
     *
     * @return the length
     */
    public BigDecimal getLength() {
        return length;
    }

    /**
     * Gets the length unknown.
     *
     * @return the length unknown
     */
    public Boolean getLengthUnknown() {
        return lengthUnknown;
    }
}

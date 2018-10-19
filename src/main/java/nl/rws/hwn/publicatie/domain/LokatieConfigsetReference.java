package nl.rws.hwn.publicatie.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class LokatieConfigsetReference.
 */
public class LokatieConfigsetReference implements Serializable {

	private static final long serialVersionUID = 8011253486284770371L;

	/** The id. */
	private String id;

	/** The editie. */
	private Date editie;

	/** The publication objects. */
	private Set<PublicationObject> publicationObjects;

	/**
	 * Default constructor for JPA.
	 */
	public LokatieConfigsetReference() {
		// EMPTY
	}

	/**
	 * Instantiates a new lokatie configset reference.
	 *
	 * @param id the id
	 * @param editie the editie
	 */
	public LokatieConfigsetReference(String id, Date editie) {
		this.id = id;
		this.editie = editie;

		publicationObjects = new HashSet<>();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the editie.
	 *
	 * @return the editie
	 */
	public Date getEditie() {
		return editie;
	}

	/**
	 * Sets the editie.
	 *
	 * @param editie the new editie
	 */
	public void setEditie(Date editie) {
		this.editie = editie;
	}

	/**
	 * Gets the publication objects.
	 *
	 * @return the publication objects
	 */
	public Set<PublicationObject> getPublicationObjects() {
		return publicationObjects;
	}

	/**
	 * Adds the.
	 *
	 * @param publicationObject the publication object
	 */
	public void add(PublicationObject publicationObject) {
		publicationObject.setConfigset(this);
		publicationObjects.add(publicationObject);
	}

	/**
	 * Checks if is editie newer than.
	 *
	 * @param editieIncoming the editie incoming
	 * @return true, if is editie newer than
	 */
	public boolean isEditieNewerThan(Date editieIncoming) {
		return editieIncoming.compareTo(editie) < 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LokatieConfigsetReference)) {
			return false;
		}

		LokatieConfigsetReference other = (LokatieConfigsetReference) obj;

		return id.equals(other.id);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}

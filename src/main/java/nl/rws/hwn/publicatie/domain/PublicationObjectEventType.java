package nl.rws.hwn.publicatie.domain;


import java.io.Serializable;
import java.util.Objects;

public class PublicationObjectEventType implements Serializable {

    private static final long serialVersionUID = -7959818309663682835L;

    private PublicationObject  publicationObject;
    private String eventClass;

    public PublicationObjectEventType(PublicationObject  publicationObject, String eventClass ) {
        this.publicationObject = publicationObject;
        this.eventClass = eventClass;
    }

    public PublicationObject getPublicationObject() {
        return publicationObject;
    }

    public String getEventClass() {
        return eventClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.publicationObject.getId() + this.eventClass);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PublicationObjectEventType)) {
            return false;
        }
        PublicationObjectEventType other = (PublicationObjectEventType) obj;
        return Objects.equals(this.publicationObject.getId()+this.eventClass , other.publicationObject.getId()+other.eventClass);
    }

}

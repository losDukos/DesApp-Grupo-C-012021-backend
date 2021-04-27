package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PublicReview extends Review {
    Boolean spoilerAlert;
    String location;

    public PublicReview() {}

    public PublicReview(String textSummary, String textExtended, Double rating,
                  Date date, String origin, String language,
                        Boolean spoilerAlert, String location) {
        super(textSummary, textExtended, rating, date, origin, language);
        this.spoilerAlert = spoilerAlert;
        this.location = location;
    }

    public Boolean getSpoilerAlert() {
        return spoilerAlert;
    }

    public void setSpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

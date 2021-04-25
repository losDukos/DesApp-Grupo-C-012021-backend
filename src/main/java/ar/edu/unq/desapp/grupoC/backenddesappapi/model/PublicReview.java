package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PublicReview extends Review {
    Boolean spoilerAlert;
    String userNick;
    String location;

    public PublicReview() {}

    public PublicReview(String textSummary, String textExtended, Double rating,
                  Date date, String origin, Integer userId, String language,
                        Boolean spoilerAlert, String userNick, String location) {
        super(textSummary, textExtended, rating, date, origin, userId, language);
        this.spoilerAlert = spoilerAlert;
        this.userNick = userNick;
        this.location = location;
    }

    public Boolean getSpoilerAlert() {
        return spoilerAlert;
    }

    public void setSpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

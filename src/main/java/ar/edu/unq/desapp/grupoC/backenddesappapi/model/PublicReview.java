package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import javax.persistence.Entity;

@Entity
public class PublicReview extends Review{
    Boolean spoilerAlert;
    Integer userNick;
    String location;

    public PublicReview() {}

    public Boolean getSpoilerAlert() {
        return spoilerAlert;
    }

    public void setSpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public Integer getUserNick() {
        return userNick;
    }

    public void setUserNick(Integer userNick) {
        this.userNick = userNick;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

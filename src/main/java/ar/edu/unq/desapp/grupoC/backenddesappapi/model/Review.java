package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public abstract class Review {

    @Id
    @GeneratedValue
    Long id;
    String textSummary;
    String textExtended;
    Integer rating;
    Boolean spoilerAlert;
    Date date;
    String origin;
    Integer userId;
    String language;

    public Review(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextSummary() {
        return textSummary;
    }

    public void setTextSummary(String textSummary) {
        this.textSummary = textSummary;
    }

    public String getTextExtended() {
        return textExtended;
    }

    public void setTextExtended(String textExtended) {
        this.textExtended = textExtended;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getSpoilerAlert() {
        return spoilerAlert;
    }

    public void setSpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

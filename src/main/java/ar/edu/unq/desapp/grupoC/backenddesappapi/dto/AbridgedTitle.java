package ar.edu.unq.desapp.grupoC.backenddesappapi.dto;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;

import java.io.Serializable;

public class AbridgedTitle implements Serializable {
    String title;
    String region;
    String language;
    Boolean isAdult;
    Integer startYear;
    Integer endYear;
    Integer runtimeMinutes;
    Double averageRating;
    Integer amountOfReviews;

    public AbridgedTitle() {}

    public AbridgedTitle(Title title) {
        this.title = title.getTitle();
        this.region = title.getRegion();
        this.language = title.getLanguage();
        this.isAdult = title.getAdult();
        this.startYear = title.getStartYear();
        this.endYear = title.getEndYear();
        this.runtimeMinutes = title.getRuntimeMinutes();
        this.averageRating = title.getAverageRating();
        this.amountOfReviews = title.getReviews().size();
    }

    public String getTitle() {
        return title;
    }

    public String getRegion() {
        return region;
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getAdult() {
        return isAdult;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public Integer getAmountOfReviews() {
        return amountOfReviews;
    }
}

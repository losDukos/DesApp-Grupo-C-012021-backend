package ar.edu.unq.desapp.grupoC.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;

import java.util.Date;

public class ReviewBuilder {

    private Boolean spoilerAlert = false;
    private String location = "test location";
    private String language = "english";
    private Boolean isPremium = false;

    public ReviewBuilder withSpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
        return this;
    }

    public ReviewBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public ReviewBuilder withLanguage(String language) {
        this.language = language;
        return this;
    }

    public ReviewBuilder withPremium(Boolean isPremium) {
        this.isPremium = isPremium;
        return this;
    }

    public Review build(Title title) {
        Review review = new Review("test summary", "extended test summary", 5.0,
                new Date(), "origin", language, spoilerAlert, location, isPremium);
        title.addReview(review);
        return review;
    }

    public Review build() {
        return new Review("test summary", "extended test summary", 5.0,
                new Date(), "origin", language, spoilerAlert, location, isPremium);
    }
}

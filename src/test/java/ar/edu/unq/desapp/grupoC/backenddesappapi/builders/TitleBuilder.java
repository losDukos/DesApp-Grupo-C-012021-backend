package ar.edu.unq.desapp.grupoC.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;

public class TitleBuilder {

    private final Title title = new Title("aTitleId", "A Title");

    public Title build() {
        return title;
    }

    public TitleBuilder withRating(Double rating) {
        Review review = new Review();
        review.setRating(rating);
        title.addReview(review);
        return this;
    }
}
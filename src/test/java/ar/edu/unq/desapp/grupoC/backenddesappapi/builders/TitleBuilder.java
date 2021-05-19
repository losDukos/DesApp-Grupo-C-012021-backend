package ar.edu.unq.desapp.grupoC.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;

import java.util.List;

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

    public TitleBuilder withGenres(List<String> genres) {
        title.setGenres(genres);
        return this;
    }

    public TitleBuilder withYear(Integer year) {
        title.setStartYear(year);
        return this;
    }
}

package ar.edu.unq.desapp.grupoC.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Actor;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;

import java.util.Collections;
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

    public TitleBuilder withActor(Actor actor) {
        title.setActors(Collections.singletonList(actor));
        return this;
    }

    public TitleBuilder withMixedReviews() {
        Review positiveReview = new Review();
        positiveReview.setRating(5.0);
        Review negativeReview = new Review();
        negativeReview.setRating(1.0);

        title.addReview(positiveReview);
        title.addReview(negativeReview);

        return this;
    }

    public TitleBuilder withTitle(String originalTitle) {
        title.setTitle(originalTitle);

        return this;
    }
}

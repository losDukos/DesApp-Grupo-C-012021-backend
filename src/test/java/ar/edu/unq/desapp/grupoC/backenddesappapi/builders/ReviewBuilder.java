package ar.edu.unq.desapp.grupoC.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;

import java.util.Date;

public class ReviewBuilder {

    public static Review buildPublicReview(Title title) {
        Review publicReview = getPublicReview();
        title.addReview(publicReview);
        return publicReview;
    }

    public static Review buildUnassociatedReview() {
        return getPublicReview();
    }

    private static Review getPublicReview() {
        return new Review("test summary", "extended test summary", 5.0, new Date(),
                "origin", "english", false, "test location", false);
    }
}

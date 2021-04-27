package ar.edu.unq.desapp.grupoC.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.PublicReview;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;

import java.util.Date;

public class ReviewBuilder {

    public static Review buildPublicReview(Title title) {
        PublicReview publicReview = getPublicReview();
        title.addReview(publicReview);
        return publicReview;
    }

    public static Review buildUnassociatedReview() {
        return getPublicReview();
    }

    private static PublicReview getPublicReview() {
        return new PublicReview("test summary", "extended test summary", 5.0, new Date(),
                "origin", "english", false, "test location");
    }
}

package ar.edu.unq.desapp.grupoC.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.PublicReview;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;

import java.util.Date;

public class ReviewBuilder {

    public static Review buildPublicReview(Title title) {
        PublicReview publicReview = new PublicReview("test summary", "extended test summary", 5.0, new Date(),
                "origin", null, "english", false, "user nick", "test location");
        title.addReview(publicReview);
        return publicReview;
    }
}

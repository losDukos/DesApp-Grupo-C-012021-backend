package ar.edu.unq.desapp.grupoC.backenddesappapi.dto;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;

import java.util.List;

public class UserMetrics {
    private List<Title> titlesReviewed;
    private List<Review> reviewsWritten;

    public UserMetrics(List<Title> titlesReviewed, List<Review> reviewsWritten) {
        this.titlesReviewed = titlesReviewed;
        this.reviewsWritten = reviewsWritten;
    }

    public List<Title> getTitlesReviewed() {
        return titlesReviewed;
    }

    public List<Review> getReviewsWritten() {
        return reviewsWritten;
    }
}

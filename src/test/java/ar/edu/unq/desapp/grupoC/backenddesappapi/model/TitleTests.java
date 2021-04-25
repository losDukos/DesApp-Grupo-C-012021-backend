package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TitleTests {

    private Title title;

    @BeforeEach
    void setUp() {
        title = new Title();
    }

    @Test
    void a_title_is_created_without_reviews() {
        assertFalse(title.hasReviews());
    }

    @Test
    void a_title_has_no_rating_without_reviews() {
        assertNull(title.getAverageRating());
    }

    @Test
    void a_title_adds_a_public_review() {
        title.addReview(new PublicReview());

        assertTrue(title.hasReviews());
    }

    @Test
    void a_title_adds_a_premium_review() {
        title.addReview(new PremiumReview());

        assertTrue(title.hasReviews());
    }

    @Test
    void a_title_with_one_review_has_an_average_rating_equal_to_that_reviews_rating() {
        Review review = new PublicReview();
        review.setRating(5.0);
        title.addReview(review);

        assertEquals(title.getAverageRating(), review.rating);
    }

    @Test
    void a_title_with_several_reviews_has_an_average_rating_equal_to_those_reviews_average_rating() {
        Review firstReview = new PublicReview();
        firstReview.setRating(5.0);
        Review secondReview = new PublicReview();
        secondReview.setRating(1.0);

        title.addReview(firstReview);
        title.addReview(secondReview);

        assertEquals(title.getAverageRating(), 3.0);
    }
}

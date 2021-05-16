package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import ar.edu.unq.desapp.grupoC.backenddesappapi.builders.ReviewBuilder;
import ar.edu.unq.desapp.grupoC.backenddesappapi.builders.UserBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ReviewTest {


    @Test
    void a_newly_created_review_has_no_appraisals() {
        Review review = new ReviewBuilder().build();

        assertFalse(review.hasAppraisals());
    }

    @Test
    void a_newly_created_review_is_positively_appraised_and_its_likes_amount_to_one() {
        Review review = new ReviewBuilder().build();
        User user = UserBuilder.buildUser();
        review.appraisePositivelyBy(user);

        assertTrue(review.hasAppraisals());
        assertEquals(review.getLikes(), 1);
    }

    @Test
    void a_newly_created_review_is_negatively_appraised_and_its_dislikes_amount_to_one() {
        Review review = new ReviewBuilder().build();
        User user = UserBuilder.buildUser();
        review.appraiseNegativelyBy(user);

        assertTrue(review.hasAppraisals());
        assertEquals(review.getDislikes(), 1);
    }

    @Test
    void a_user_changes_their_dislike_to_like_and_the_amount_of_likes_turns_to_one() {
        Review review = new ReviewBuilder().build();
        User user = UserBuilder.buildUser();
        review.appraiseNegativelyBy(user);
        review.appraisePositivelyBy(user);

        assertTrue(review.hasAppraisals());
        assertEquals(review.getDislikes(), 0);
        assertEquals(review.getLikes(), 1);
    }
}

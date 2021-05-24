package ar.edu.unq.desapp.grupoC.backenddesappapi.services;

import ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications.*;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByTitle(String title) {
        return reviewRepository.findAllByReviewedTitleTitleIgnoreCaseContaining(title);
    }

    public List<Review> getReviewsByTitleId(String id, String type, String language, String location, Boolean spoilerAlert, Pageable pageable) {
        Predicate predicate = new BooleanBuilder()
                .and(ReviewByTitleId.get(id))
                .and(ReviewByType.get(type))
                .and(ReviewByLanguage.get(language))
                .and(ReviewByLocation.get(location))
                .and(ReviewBySpoilerAlert.get(spoilerAlert));

        return reviewRepository.findAll(predicate, pageable).getContent();
    }

    public Review getReviewById(Long id) { return reviewRepository.findById(id).get(); }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }
}

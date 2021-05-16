package ar.edu.unq.desapp.grupoC.backenddesappapi.services;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByTitle(String title) {
        return reviewRepository.findAllByReviewedTitleTitleIgnoreCaseContaining(title);
    }

    public List<Review> getReviewsByTitleId(Specification<Review> specs, Pageable pageable) {
        return reviewRepository.findAll(specs, pageable).getContent();
    }

    public Review getReviewById(Long id) { return reviewRepository.findById(id).get(); }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }
}

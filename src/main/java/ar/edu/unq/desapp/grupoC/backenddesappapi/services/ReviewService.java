package ar.edu.unq.desapp.grupoC.backenddesappapi.services;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByTitle(String title) {
        return reviewRepository.findAllByReviewedTitleTitleIgnoreCaseContaining(title);
    }

    public List<Review> getReviewsByTitleId(String id) {
        return reviewRepository.findAllByReviewedTitleTitleId(id);
    }

    public Review getReviewById(Long id) { return reviewRepository.findById(id).get(); }
}

package ar.edu.unq.desapp.grupoC.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository  extends CrudRepository<Review, Long> {
    List<Review> findAll();
    List<Review> findAllByReviewedTitleTitleIgnoreCaseContaining(String title);
}

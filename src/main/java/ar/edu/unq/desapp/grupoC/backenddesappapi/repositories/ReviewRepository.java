package ar.edu.unq.desapp.grupoC.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository  extends CrudRepository<Review, Long> {
    List<Review> findAll();

    List<Review> findAllByReviewedTitleTitleIgnoreCaseContaining(String title);

    List<Review> findAllByReviewedTitleTitleId(String id);

    Optional<Review> findById(Long id);
}

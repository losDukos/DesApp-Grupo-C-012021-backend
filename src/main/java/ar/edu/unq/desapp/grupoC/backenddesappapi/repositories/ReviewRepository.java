package ar.edu.unq.desapp.grupoC.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository  extends CrudRepository<Review, Long>, JpaSpecificationExecutor<Review> {
    List<Review> findAll();

    List<Review> findAllByReviewedTitleTitleIgnoreCaseContaining(String title);

    List<Review> findAll(Specification<Review> specs);

    Optional<Review> findById(Long id);
}

package ar.edu.unq.desapp.grupoC.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository  extends PagingAndSortingRepository<Review, Long>, JpaSpecificationExecutor<Review> {
    List<Review> findAll();

    List<Review> findAllByReviewedTitleTitleIgnoreCaseContaining(String title);

    Page<Review> findAll(Specification<Review> specs, Pageable pageable);

    Optional<Review> findById(Long id);
}

package ar.edu.unq.desapp.grupoC.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository  extends PagingAndSortingRepository<Review, Long>, QuerydslPredicateExecutor<Review> {
    List<Review> findAll();

    List<Review> findAllByReviewedTitleTitleIgnoreCaseContaining(String title);

    Page<Review> findAll(Predicate predicate, Pageable pageable);

    Optional<Review> findById(Long id);
}

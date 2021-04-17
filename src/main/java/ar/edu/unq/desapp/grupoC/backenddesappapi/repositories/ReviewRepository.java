package ar.edu.unq.desapp.grupoC.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository  extends CrudRepository<Review, Long> {
}

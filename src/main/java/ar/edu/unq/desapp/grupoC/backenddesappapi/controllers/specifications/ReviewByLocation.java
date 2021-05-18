package ar.edu.unq.desapp.grupoC.backenddesappapi.controllers.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ReviewByLocation implements Specification<Review> {

    private final String location;

    public ReviewByLocation(String location) {
        this.location = location;
    }

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (location == null) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.equal(root.get("location"), location);
    }
}

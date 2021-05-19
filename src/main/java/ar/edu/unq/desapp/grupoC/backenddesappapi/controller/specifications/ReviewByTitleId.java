package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ReviewByTitleId implements Specification<Review> {

    private final String titleId;

    public ReviewByTitleId(String titleId) {
        this.titleId = titleId;
    }

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("reviewedTitle").get("titleId"), titleId);
    }
}

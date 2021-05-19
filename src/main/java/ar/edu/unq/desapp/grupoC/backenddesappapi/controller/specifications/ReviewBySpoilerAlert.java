package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ReviewBySpoilerAlert implements Specification<Review> {

    private final Boolean spoilerAlert;

    public ReviewBySpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (spoilerAlert == null) {
           return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.equal(root.get("spoilerAlert"), this.spoilerAlert);
    }
}

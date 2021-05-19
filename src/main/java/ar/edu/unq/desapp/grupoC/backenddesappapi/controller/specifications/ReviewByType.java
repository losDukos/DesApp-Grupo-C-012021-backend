package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ReviewByType implements Specification<Review> {

    private final String type;

    public ReviewByType(String type) {
        this.type = type;
    }

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (type == null) {
            return criteriaBuilder.conjunction();
        }
        Boolean mustBePremium = type.equals("critic");
        return criteriaBuilder.equal(root.get("isPremium"), mustBePremium);
    }
}

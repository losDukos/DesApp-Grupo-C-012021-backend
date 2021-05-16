package ar.edu.unq.desapp.grupoC.backenddesappapi.controllers.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ReviewByLanguage implements Specification<Review> {

    private final String language;

    public ReviewByLanguage(String language) {
        this.language = language;
    }

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (language == null) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.equal(root.get("language"), language);
    }
}

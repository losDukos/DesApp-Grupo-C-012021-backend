package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TitleByMaxRating implements Specification<Title> {
    private final Double rating;

    public TitleByMaxRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public Predicate toPredicate(Root<Title> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (rating == null) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.lessThanOrEqualTo(root.get("averageRating"), rating);
    }
}

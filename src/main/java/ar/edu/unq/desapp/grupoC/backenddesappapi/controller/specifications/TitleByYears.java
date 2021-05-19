package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TitleByYears implements Specification<Title> {
    private final Integer fromYear;
    private final Integer toYear;

    public TitleByYears(Integer fromYear, Integer toYear) {
        this.fromYear = fromYear;
        this.toYear = toYear;
    }

    @Override
    public Predicate toPredicate(Root<Title> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (fromYear == null && toYear == null) {
            return criteriaBuilder.conjunction();
        } else if (fromYear == null) {
            return criteriaBuilder.lessThanOrEqualTo(root.get("startYear"), toYear);
        } else if (toYear == null) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("startYear"), fromYear);
        }
        return criteriaBuilder.between(root.get("startYear"), fromYear, toYear);
    }
}

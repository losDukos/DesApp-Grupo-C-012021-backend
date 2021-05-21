package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TitleByActor implements Specification<Title> {

    private final String name;

    public TitleByActor(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<Title> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (name == null) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.equal(root.join("actors").get("name"), name);
    }
}

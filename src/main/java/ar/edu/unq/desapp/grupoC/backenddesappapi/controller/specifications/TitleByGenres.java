package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

public class TitleByGenres implements Specification<Title> {
    private final List<String> genres;

    public TitleByGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public Predicate toPredicate(Root<Title> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (genres == null) {
            return criteriaBuilder.conjunction();
        }
        List<Predicate> predicates = genres.stream().map(genre -> {
            String formattedGenre = "%" + genre + "%";
            return criteriaBuilder.like(root.get("genresString"), formattedGenre);
        }).collect(Collectors.toList());
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

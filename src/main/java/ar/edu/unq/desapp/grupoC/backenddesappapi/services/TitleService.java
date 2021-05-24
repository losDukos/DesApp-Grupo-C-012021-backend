package ar.edu.unq.desapp.grupoC.backenddesappapi.services;

import ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications.*;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.TitleRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {

    @Autowired
    TitleRepository titleRepository;

    public List<Title> getTitles(Double minRating, Double maxRating, List<String> genres, Integer fromYear,
                                 Integer toYear, String actor, Pageable pageable) {
        Predicate predicate = new BooleanBuilder()
                .and(TitleByMinRating.get(minRating))
                .and(TitleByMaxRating.get(maxRating))
                .and(TitleByGenres.get(genres))
                .and(TitleByYears.get(fromYear, toYear))
                .and(TitleByActor.get(actor));

        return titleRepository.findAll(predicate, pageable).getContent();
    }
}

package ar.edu.unq.desapp.grupoC.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications.*;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.TitleService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "title")
public class TitleController {

    @Autowired
    TitleService titleService;

    @GetMapping
    public List<Title> getTitles(@RequestParam(required = false) Double minRating,
                                 @RequestParam(required = false) Double maxRating,
                                 @RequestParam(required = false) List<String> genres,
                                 @RequestParam(required = false) Integer fromYear,
                                 @RequestParam(required = false) Integer toYear,
                                 @RequestParam(required = false) String actor,
                                 Pageable pageable) {

        return titleService.getTitles(minRating, maxRating, genres, fromYear, toYear, actor, pageable);
    }
}

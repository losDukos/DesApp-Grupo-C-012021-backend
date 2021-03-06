package ar.edu.unq.desapp.grupoC.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoC.backenddesappapi.dto.AbridgedTitle;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
                                 @RequestParam(required = false) Integer minReviews,
                                 Pageable pageable) {

        return titleService.getTitles(minRating, maxRating, genres, fromYear, toYear, actor, minReviews, pageable);
    }

    @GetMapping(path = "/{name}")
    public AbridgedTitle getAbridgedTitleByTitle(@PathVariable String name) {
        Title retrievedTitle = titleService.getAbridgedTitle(name);
        return new AbridgedTitle(retrievedTitle);
    }
}

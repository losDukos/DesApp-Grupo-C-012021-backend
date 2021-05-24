package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.QTitle;
import com.querydsl.core.BooleanBuilder;
import java.util.List;

public class TitleByGenres {
    public static BooleanBuilder get(List<String> genres) {
        if(genres == null) {
            return null;
        }

        QTitle title = QTitle.title1;
        BooleanBuilder hasAllGenres = new BooleanBuilder();
        genres.forEach(genre -> hasAllGenres.and(title.genresString.containsIgnoreCase(genre)));

        return hasAllGenres;
    }
}

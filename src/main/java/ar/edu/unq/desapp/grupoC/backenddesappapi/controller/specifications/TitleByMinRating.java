package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.QTitle;
import com.querydsl.core.types.dsl.BooleanExpression;

public class TitleByMinRating {
    public static BooleanExpression get(Double rating) {
        if(rating == null) {
            return null;
        }

        QTitle title = QTitle.title1;
        return title.averageRating.goe(rating);
    }
}

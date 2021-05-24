package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.QTitle;
import com.querydsl.core.types.dsl.BooleanExpression;

public class TitleWellReviewed {

    public static BooleanExpression get(Integer reviews) {
        if (reviews == null) {
            return null;
        }

        QTitle title = QTitle.title1;
        return title.reviews.size().goe(reviews);
    }
}

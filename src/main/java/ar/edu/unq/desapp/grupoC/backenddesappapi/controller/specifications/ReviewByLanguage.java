package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.QReview;
import com.querydsl.core.types.dsl.BooleanExpression;

public class ReviewByLanguage {

    public static BooleanExpression get(String language) {
        if (language == null) {
            return null;
        }

        QReview review = QReview.review;
        return review.language.eq(language);
    }
}

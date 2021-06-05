package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.QReview;
import com.querydsl.core.types.dsl.BooleanExpression;

public class ReviewBySpoilerAlert {

    public static BooleanExpression get(Boolean spoilerAlert) {
        if (spoilerAlert == null) {
            return null;
        }

        QReview review = QReview.review;
        return review.spoilerAlert.eq(spoilerAlert);
    }
}

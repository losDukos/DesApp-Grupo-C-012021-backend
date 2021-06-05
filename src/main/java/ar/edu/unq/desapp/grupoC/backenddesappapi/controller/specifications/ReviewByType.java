package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.QReview;
import com.querydsl.core.types.dsl.BooleanExpression;

public class ReviewByType {
    public static BooleanExpression get(String type) {
        if (type == null) {
            return null;
        }

        QReview review = QReview.review;
        Boolean mustBePremium = type.equals("critic");
        return review.isPremium.eq(mustBePremium);
    }
}

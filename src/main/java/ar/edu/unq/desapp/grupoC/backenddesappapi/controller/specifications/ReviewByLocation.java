package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.QReview;
import com.querydsl.core.types.dsl.BooleanExpression;

public class ReviewByLocation {


    public static BooleanExpression get(String location) {
        if (location == null) {
            return null;
        }

        QReview review = QReview.review;
        return review.location.eq(location);
    }
}

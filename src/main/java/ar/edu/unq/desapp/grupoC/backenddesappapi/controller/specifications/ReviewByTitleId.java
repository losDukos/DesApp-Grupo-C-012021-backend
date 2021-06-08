package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.QReview;
import com.querydsl.core.types.dsl.BooleanExpression;

public class ReviewByTitleId {

    public static BooleanExpression get(String titleId) {
        QReview review = QReview.review;
        return review.reviewedTitle.titleId.eq(titleId);
    }
}

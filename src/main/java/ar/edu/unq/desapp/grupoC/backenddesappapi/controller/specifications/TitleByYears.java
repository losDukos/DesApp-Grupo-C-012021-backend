package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.QTitle;
import com.querydsl.core.BooleanBuilder;

public class TitleByYears {

    public static BooleanBuilder get(Integer fromYear, Integer toYear) {
        QTitle title = QTitle.title1;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(fromYear != null) {
            booleanBuilder.and(title.startYear.goe(fromYear));
        }
        if(toYear != null) {
            booleanBuilder.and(title.startYear.loe(toYear));
        }

        return booleanBuilder;
    }
}

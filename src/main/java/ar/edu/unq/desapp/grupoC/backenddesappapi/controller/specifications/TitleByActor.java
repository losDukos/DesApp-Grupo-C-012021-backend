package ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.QTitle;
import com.querydsl.core.types.dsl.BooleanExpression;

public class TitleByActor {

    public static BooleanExpression get(String name) {
        if(name == null) {
            return null;
        }

        QTitle title = QTitle.title1;
        return title.actors.any().name.eq(name);
    }
}

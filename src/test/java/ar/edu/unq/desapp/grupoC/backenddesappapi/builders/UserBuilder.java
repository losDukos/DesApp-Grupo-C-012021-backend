package ar.edu.unq.desapp.grupoC.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.User;

public class UserBuilder {

    public static User buildUser() {
        User user = new User();
        user.setId((long) 1);
        user.setName("Test User");
        return user;
    }
}

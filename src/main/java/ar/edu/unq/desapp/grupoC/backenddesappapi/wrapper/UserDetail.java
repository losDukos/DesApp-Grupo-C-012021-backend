package ar.edu.unq.desapp.grupoC.backenddesappapi.wrapper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class UserDetail extends User {
    private Long id;

    public UserDetail(String username, String password, Long id){
        super(username, password, Collections.emptyList());
        this.id = id;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}

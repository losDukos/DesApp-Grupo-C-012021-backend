package ar.edu.unq.desapp.grupoC.backenddesappapi.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final Long id;
    private final String username;

    public JwtResponse(String token, Long id, String username) {
        this.token = token;
        this.id = id;
        this.username = username;
    }

    public String getToken() {
        return this.token;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() { return username; }
}

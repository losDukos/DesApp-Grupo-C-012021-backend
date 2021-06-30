package ar.edu.unq.desapp.grupoC.backenddesappapi.security;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

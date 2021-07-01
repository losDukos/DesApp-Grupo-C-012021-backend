package ar.edu.unq.desapp.grupoC.backenddesappapi.dto;

import javax.validation.constraints.NotEmpty;

public class UserRegisterDto {
    @NotEmpty(message = "Username is mandatory")
    String username;
    @NotEmpty(message = "Password is mandatory")
    String password;
    @NotEmpty(message = "Email is mandatory")
    String mail;

    public UserRegisterDto(String username, String pass, String mail){
        this.username = username;
        this.password = pass;
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

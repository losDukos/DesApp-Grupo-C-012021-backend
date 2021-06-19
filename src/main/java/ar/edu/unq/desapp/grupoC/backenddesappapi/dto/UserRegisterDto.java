package ar.edu.unq.desapp.grupoC.backenddesappapi.dto;

public class UserRegisterDto {
    String name;
    String password;
    String mail;

    public UserRegisterDto(String name, String pass, String mail){
        this.name = name;
        this.password = pass;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

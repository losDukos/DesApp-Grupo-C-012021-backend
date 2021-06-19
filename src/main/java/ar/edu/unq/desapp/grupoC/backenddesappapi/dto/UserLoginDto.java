package ar.edu.unq.desapp.grupoC.backenddesappapi.dto;

public class UserLoginDto {
    String mail;
    String password;

    public UserLoginDto( String mail, String password){
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

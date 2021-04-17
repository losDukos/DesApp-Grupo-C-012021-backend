package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

public class PublicReview extends Review{
    Integer userNick;
    String location;

    public PublicReview(){

    }
    public Integer getUserNick() {
        return userNick;
    }

    public void setUserNick(Integer userNick) {
        this.userNick = userNick;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

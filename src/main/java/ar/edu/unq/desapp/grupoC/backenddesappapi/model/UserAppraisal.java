package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

public class UserAppraisal {
    Long userId;
    Boolean isPositive;

    public UserAppraisal(Long id, Boolean isPositive){
        this.userId = id;
        this.isPositive = isPositive;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getPositive() {
        return isPositive;
    }

    public void setPositive(Boolean positive) {
        isPositive = positive;
    }
}

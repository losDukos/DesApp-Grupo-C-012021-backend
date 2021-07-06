package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Appraisal {
    @Id
    @GeneratedValue
    private Long id;
    private Boolean isPositive;

    @OneToOne
    @JsonIgnoreProperties({"password", "token", "email"})
    private User user;

    public Appraisal() {}

    public Appraisal(User user, Boolean isPositive) {
        this.user = user;
        this.isPositive = isPositive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Boolean isPositive() {
        return isPositive;
    }

    public Boolean getPositive() {
        return isPositive;
    }

    public void setPositive(Boolean positive) {
        isPositive = positive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

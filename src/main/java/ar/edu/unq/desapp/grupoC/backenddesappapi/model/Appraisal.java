package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Appraisal {
    @Id
    private Long id;
    private Boolean isPositive;

    @OneToOne
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

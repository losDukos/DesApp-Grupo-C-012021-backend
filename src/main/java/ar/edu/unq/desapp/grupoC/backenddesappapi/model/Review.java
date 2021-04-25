package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Review {

    @Id
    @GeneratedValue
    Long id;
    String textSummary;
    String textExtended;
    Double rating;
    Date date;
    String origin;
    Integer userId;
    String language;

    @ManyToOne
    @JsonIgnoreProperties({"ordering", "title", "region", "language", "types", "attributes", "titleType",
            "primaryTitle", "originalTitle", "startYear", "endYear", "runtimeMinutes", "genres",
            "reviews", "adult"})
    Title reviewedTitle;

    public Review() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextSummary() {
        return textSummary;
    }

    public void setTextSummary(String textSummary) {
        this.textSummary = textSummary;
    }

    public String getTextExtended() {
        return textExtended;
    }

    public void setTextExtended(String textExtended) {
        this.textExtended = textExtended;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Title getReviewedTitle() {
        return reviewedTitle;
    }

    public void setReviewedTitle(Title reviewedTitle) {
        this.reviewedTitle = reviewedTitle;
    }
}

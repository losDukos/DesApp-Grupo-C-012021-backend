package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {

    @Id
    @GeneratedValue
    Long id;
    String textSummary;
    String textExtended;
    // TODO: Make sure this cannot be null
    Double rating;
    Date date;
    String platform;
    String origin;
    String language;
    Boolean spoilerAlert;
    String location;
    Boolean isPremium = false;
    // TODO:You must have a minimum number of reports for the review to be reported.
    Boolean isReported = false;

    @ManyToOne
    @JsonIgnoreProperties({"ordering", "region", "language", "types", "attributes", "isOriginalTitle", "titleType",
            "primaryTitle", "originalTitle", "startYear", "endYear", "runtimeMinutes", "genres",
            "reviews", "adult"})
    Title reviewedTitle;

    @OneToOne
    @JsonIgnoreProperties({"password"})
    User user;

    @OneToMany(cascade=CascadeType.ALL)
    List<Appraisal> appraisals = new ArrayList<>();

    public Review() {}

    public Review(String textSummary, String textExtended, Double rating,
                  Date date, String origin, String language, Boolean spoilerAlert,
                  String location, Boolean isPremium) {
        this.textSummary = textSummary;
        this.textExtended = textExtended;
        this.rating = rating;
        this.date = date;
        this.origin = origin;
        this.language = language;
        this.spoilerAlert = spoilerAlert;
        this.location = location;
        this.isPremium = isPremium;
        this.isReported = false;
    }

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
        if (reviewedTitle != null) {
            reviewedTitle.calculateAverageRating(rating);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getSpoilerAlert() {
        return spoilerAlert;
    }

    public void setSpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    public Boolean getReported() {
        return isReported;
    }

    public void setReported(Boolean reported) {
        isReported = reported;
    }

    public Title getReviewedTitle() {
        return reviewedTitle;
    }

    public void setReviewedTitle(Title reviewedTitle) {
        this.reviewedTitle = reviewedTitle;
    }

    public List<Appraisal> getAppraisals() {
        return appraisals;
    }

    public void setAppraisals(List<Appraisal> appraisals) {
        this.appraisals = appraisals;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean hasAppraisals() {
        return !appraisals.isEmpty();
    }

    public void appraisePositivelyBy(User user) {
        setAppraisalBy(user, true);
    }

    public void appraiseNegativelyBy(User user) {
        setAppraisalBy(user, false);
    }

    public Optional<Appraisal> getUserAppraisal(User user) {
        return appraisals.stream().filter(appraisal -> appraisal.getUser().getId().equals(user.getId())).findFirst();
    }

    private void setAppraisalBy(User user, Boolean isPositive) {
        Optional<Appraisal> appraisal = getUserAppraisal(user);
        if (appraisal.isEmpty()) {
            appraisals.add(new Appraisal(user, isPositive));
        } else {
            appraisal.get().setPositive(isPositive);
        }
    }

    @Transient
    public Long getLikes() {
        return appraisals.stream().filter(Appraisal::isPositive).count();
    }

    @Transient
    public Long getDislikes() {
        return appraisals.stream().filter(appraisal -> !appraisal.isPositive()).count();
    }
}

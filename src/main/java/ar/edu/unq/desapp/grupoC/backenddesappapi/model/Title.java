package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Title {
    @Id
    String titleId;
    Integer ordering;
    String title;
    String region;
    String language;
    @ElementCollection(targetClass=String.class)
    List<String> types;
    @ElementCollection(targetClass=String.class)
    List<String> attributes;
    Boolean isOriginalTitle;
    String tcons;
    String titleType;
    String primaryTitle;
    String originalTitle;
    Boolean isAdult;
    Date startYear;
    Date endYear;
    Integer runtimeMinutes;
    @ElementCollection(targetClass=String.class)
    List<String> genres;



    //Ratings
    //String tconst;
    String averageRating; //Revisar
    Integer numVotes;

    @OneToMany
    List<PublicReview> publicReviews;
    @OneToMany
    List<PremiumReview> premiumReviews;

    public Title(){

    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public Boolean getOriginalTitle() {
        return isOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Boolean getAdult() {
        return isAdult;
    }

    public void setAdult(Boolean adult) {
        isAdult = adult;
    }

    public Date getStartYear() {
        return startYear;
    }

    public void setStartYear(Date startYear) {
        this.startYear = startYear;
    }

    public Date getEndYear() {
        return endYear;
    }

    public void setEndYear(Date endYear) {
        this.endYear = endYear;
    }

    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(Integer runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(Integer numVotes) {
        this.numVotes = numVotes;
    }

    public List<PublicReview> getPublicReviews() {
        return publicReviews;
    }

    public void setPublicReviews(List<PublicReview> publicReviews) {
        this.publicReviews = publicReviews;
    }

    public List<PremiumReview> getPremiumReviews() {
        return premiumReviews;
    }

    public void setPremiumReviews(List<PremiumReview> premiumReviews) {
        this.premiumReviews = premiumReviews;
    }

    public void setOriginalTitle(Boolean originalTitle) {
        isOriginalTitle = originalTitle;
    }

    public String getTcons() {
        return tcons;
    }

    public void setTcons(String tcons) {
        this.tcons = tcons;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }
}

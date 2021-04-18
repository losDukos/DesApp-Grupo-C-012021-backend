package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import ar.edu.unq.desapp.grupoC.backenddesappapi.converters.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Title {
    // AKAs
    @Id
    String titleId;
    Integer ordering;
    String title;
    String region;
    String language;
    @Convert(converter = StringListConverter.class)
    List<String> types;
    @Convert(converter = StringListConverter.class)
    List<String> attributes;
    Boolean isOriginalTitle;

    // Basics
    String titleType;
    String primaryTitle;
    String originalTitle;
    Boolean isAdult;
    Integer startYear;
    Integer endYear;
    Integer runtimeMinutes;
    @Convert(converter = StringListConverter.class)
    List<String> genres;

    // Ratings
    Double averageRating;
    Integer numVotes;

    // Reviews
    @OneToMany
    List<PublicReview> publicReviews;
    @OneToMany
    List<PremiumReview> premiumReviews;

    public Title() {}

    public Title(String titleId, Integer ordering, String title, String region, String language, List<String> types,
                 List<String> attributes, Boolean isOriginalTitle, String titleType, String primaryTitle,
                 String originalTitle, Boolean isAdult, Integer startYear, Integer endYear, Integer runtimeMinutes,
                 List<String> genres, Double averageRating, Integer numVotes) {
        this.titleId = titleId;
        this.ordering = ordering;
        this.title = title;
        this.region = region;
        this.language = language;
        this.types = types;
        this.attributes = attributes;
        this.isOriginalTitle = isOriginalTitle;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.genres = genres;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
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

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
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

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
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

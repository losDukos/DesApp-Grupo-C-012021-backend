package ar.edu.unq.desapp.grupoC.backenddesappapi.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Episode extends Title {
    String parentTitleId;
    Integer seasonNumber;
    Integer episodeNumber;

    public Episode() {}

    public Episode(String titleId, String titleType, String primaryTitle, String originalTitle, Boolean isAdult,
                 Integer startYear, Integer endYear, Integer runtimeMinutes, List<String> genres,
                 Double averageRating, Integer numVotes, String parentTconst, Integer seasonNumber, Integer episodeNumber) {
        this.titleId = titleId;
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
        this.parentTitleId = parentTconst;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }

    public String getParentTitleId() {
        return parentTitleId;
    }

    public void setParentTitleId(String parentTitleId) {
        this.parentTitleId = parentTitleId;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
}

package com.etiennelawlor.moviehub.domain.models;

import java.util.Date;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieReleaseDatesDomainModel {

    // region Fields
    public int id;
    public List<MovieReleaseDateDomainModel> movieReleaseDates = null;
    private Date expiredAt;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<MovieReleaseDateDomainModel> getMovieReleaseDates() {
        return movieReleaseDates;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieReleaseDates(List<MovieReleaseDateDomainModel> movieReleaseDates) {
        this.movieReleaseDates = movieReleaseDates;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    // endregion

    @Override
    public String toString() {
        return "MovieReleaseDatesDomainModel{" +
                "id=" + id +
                ", movieReleaseDates=" + movieReleaseDates +
                ", expiredAt=" + expiredAt +
                '}';
    }
}

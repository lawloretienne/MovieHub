package com.etiennelawlor.moviehub.presentation.models;

import java.util.Date;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieReleaseDatesPresentationModel {

    // region Fields
    public int id;
    public List<MovieReleaseDatePresentationModel> movieReleaseDates = null;
    private Date expiredAt;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<MovieReleaseDatePresentationModel> getMovieReleaseDates() {
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

    public void setMovieReleaseDates(List<MovieReleaseDatePresentationModel> movieReleaseDates) {
        this.movieReleaseDates = movieReleaseDates;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    // endregion

    @Override
    public String toString() {
        return "MovieReleaseDatesPresentationModel{" +
                "id=" + id +
                ", movieReleaseDates=" + movieReleaseDates +
                ", expiredAt=" + expiredAt +
                '}';
    }
}

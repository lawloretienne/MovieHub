package com.etiennelawlor.moviehub.data.repositories.models;

import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDateEnvelope;

import java.util.Date;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieReleaseDatesDataModel {

    // region Fields
    public int id;
    public List<MovieReleaseDateEnvelope> movieReleaseDateEnvelopes = null;
    private Date expiredAt;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<MovieReleaseDateEnvelope> getMovieReleaseDateEnvelopes() {
        return movieReleaseDateEnvelopes;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieReleaseDateEnvelopes(List<MovieReleaseDateEnvelope> movieReleaseDateEnvelopes) {
        this.movieReleaseDateEnvelopes = movieReleaseDateEnvelopes;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    // endregion

    @Override
    public String toString() {
        return "MovieReleaseDatesDataModel{" +
                "id=" + id +
                ", movieReleaseDateEnvelopes=" + movieReleaseDateEnvelopes +
                ", expiredAt=" + expiredAt +
                '}';
    }
}

package com.etiennelawlor.moviehub.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class MovieReleaseDatesEnvelope {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("results")
    public List<MovieReleaseDateEnvelope> movieReleaseDateEnvelopes = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<MovieReleaseDateEnvelope> getMovieReleaseDateEnvelopes() {
        return movieReleaseDateEnvelopes;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieReleaseDateEnvelopes(List<MovieReleaseDateEnvelope> movieReleaseDateEnvelopes) {
        this.movieReleaseDateEnvelopes = movieReleaseDateEnvelopes;
    }

    // endregion
}

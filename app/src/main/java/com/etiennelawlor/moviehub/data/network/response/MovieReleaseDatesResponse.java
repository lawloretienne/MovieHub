package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class MovieReleaseDatesResponse {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("results")
    public List<MovieReleaseDateResponse> movieReleaseDates = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<MovieReleaseDateResponse> getMovieReleaseDates() {
        return movieReleaseDates;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieReleaseDates(List<MovieReleaseDateResponse> movieReleaseDates) {
        this.movieReleaseDates = movieReleaseDates;
    }

    // endregion

    @Override
    public String toString() {
        return "MovieReleaseDatesResponse{" +
                "id=" + id +
                ", movieReleaseDates=" + movieReleaseDates +
                '}';
    }
}

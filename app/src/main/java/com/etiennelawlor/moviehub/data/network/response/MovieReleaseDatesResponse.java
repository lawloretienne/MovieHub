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
    public List<MovieReleaseDateResponse> movieReleaseDateResponses = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<MovieReleaseDateResponse> getMovieReleaseDateResponses() {
        return movieReleaseDateResponses;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieReleaseDateResponses(List<MovieReleaseDateResponse> movieReleaseDateResponses) {
        this.movieReleaseDateResponses = movieReleaseDateResponses;
    }

    // endregion

    @Override
    public String toString() {
        return "MovieReleaseDatesResponse{" +
                "id=" + id +
                ", movieReleaseDateResponses=" + movieReleaseDateResponses +
                '}';
    }
}

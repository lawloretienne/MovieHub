package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class MovieCreditsResponse {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("cast")
    public List<MovieCreditResponse> cast = null;
    @SerializedName("crew")
    public List<MovieCreditResponse> crew = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<MovieCreditResponse> getCast() {
        return cast;
    }

    public List<MovieCreditResponse> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<MovieCreditResponse> cast) {
        this.cast = cast;
    }

    public void setCrew(List<MovieCreditResponse> crew) {
        this.crew = crew;
    }

    // endregion

    @Override
    public String toString() {
        return "MovieCreditsResponse{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

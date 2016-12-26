package com.etiennelawlor.moviehub.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class MovieCreditsEnvelope {

    // region Fields
    @SerializedName("id")
    public Integer id;
    @SerializedName("cast")
    public List<MovieCredit> cast = null;
    @SerializedName("crew")
    public List<MovieCredit> crew = null;
    // endregion

    // region Getters

    public Integer getId() {
        return id;
    }

    public List<MovieCredit> getCast() {
        return cast;
    }

    public List<MovieCredit> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCast(List<MovieCredit> cast) {
        this.cast = cast;
    }

    public void setCrew(List<MovieCredit> crew) {
        this.crew = crew;
    }

    // endregion
}

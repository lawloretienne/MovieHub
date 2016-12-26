package com.etiennelawlor.moviehub.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class TelevisionShowCreditsEnvelope {

    // region Fields
    @SerializedName("id")
    public Integer id;
    @SerializedName("cast")
    public List<TelevisionShowCredit> cast = null;
    @SerializedName("crew")
    public List<TelevisionShowCredit> crew = null;
    // endregion

    // region Getters

    public Integer getId() {
        return id;
    }

    public List<TelevisionShowCredit> getCast() {
        return cast;
    }

    public List<TelevisionShowCredit> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCast(List<TelevisionShowCredit> cast) {
        this.cast = cast;
    }

    public void setCrew(List<TelevisionShowCredit> crew) {
        this.crew = crew;
    }

    // endregion
}

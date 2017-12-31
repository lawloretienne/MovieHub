package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class TelevisionShowCreditsResponse {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("cast")
    public List<TelevisionShowCredit> cast = null;
    @SerializedName("crew")
    public List<TelevisionShowCredit> crew = null;
    // endregion

    // region Getters

    public int getId() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<TelevisionShowCredit> cast) {
        this.cast = cast;
    }

    public void setCrew(List<TelevisionShowCredit> crew) {
        this.crew = crew;
    }

    // endregion

    @Override
    public String toString() {
        return "TelevisionShowCreditsResponse{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

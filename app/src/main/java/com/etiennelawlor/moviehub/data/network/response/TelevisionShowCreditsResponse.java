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
    public List<TelevisionShowCreditResponse> cast = null;
    @SerializedName("crew")
    public List<TelevisionShowCreditResponse> crew = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<TelevisionShowCreditResponse> getCast() {
        return cast;
    }

    public List<TelevisionShowCreditResponse> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<TelevisionShowCreditResponse> cast) {
        this.cast = cast;
    }

    public void setCrew(List<TelevisionShowCreditResponse> crew) {
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

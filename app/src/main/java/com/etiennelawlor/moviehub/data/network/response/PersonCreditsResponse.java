package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class PersonCreditsResponse {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("cast")
    public List<PersonCreditResponse> cast = null;
    @SerializedName("crew")
    public List<PersonCreditResponse> crew = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<PersonCreditResponse> getCast() {
        return cast;
    }

    public List<PersonCreditResponse> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<PersonCreditResponse> cast) {
        this.cast = cast;
    }

    public void setCrew(List<PersonCreditResponse> crew) {
        this.crew = crew;
    }

    // endregion

    @Override
    public String toString() {
        return "PersonCreditsResponse{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

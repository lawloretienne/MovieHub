package com.etiennelawlor.moviehub.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class PersonCreditsEnvelope {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("cast")
    public List<PersonCredit> cast = null;
    @SerializedName("crew")
    public List<PersonCredit> crew = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<PersonCredit> getCast() {
        return cast;
    }

    public List<PersonCredit> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<PersonCredit> cast) {
        this.cast = cast;
    }

    public void setCrew(List<PersonCredit> crew) {
        this.crew = crew;
    }

    // endregion

    @Override
    public String toString() {
        return "PersonCreditsEnvelope{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

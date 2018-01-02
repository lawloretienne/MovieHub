package com.etiennelawlor.moviehub.presentation.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonCreditsPresentationModel {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("cast")
    public List<PersonCreditPresentationModel> cast = null;
    @SerializedName("crew")
    public List<PersonCreditPresentationModel> crew = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<PersonCreditPresentationModel> getCast() {
        return cast;
    }

    public List<PersonCreditPresentationModel> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<PersonCreditPresentationModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<PersonCreditPresentationModel> crew) {
        this.crew = crew;
    }

    // endregion


    @Override
    public String toString() {
        return "PersonCreditsPresentationModel{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

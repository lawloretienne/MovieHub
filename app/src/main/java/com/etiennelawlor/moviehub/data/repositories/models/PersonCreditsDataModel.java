package com.etiennelawlor.moviehub.data.repositories.models;

import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonCreditsDataModel {

    // region Fields
    public int id;
    public List<PersonCreditDataModel> cast = null;
    public List<PersonCreditDataModel> crew = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<PersonCreditDataModel> getCast() {
        return cast;
    }

    public List<PersonCreditDataModel> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<PersonCreditDataModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<PersonCreditDataModel> crew) {
        this.crew = crew;
    }

    // endregion

    @Override
    public String toString() {
        return "PersonCreditsDataModel{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

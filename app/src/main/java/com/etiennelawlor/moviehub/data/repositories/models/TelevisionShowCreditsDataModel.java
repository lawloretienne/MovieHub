package com.etiennelawlor.moviehub.data.repositories.models;

import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditsDataModel {

    // region Fields
    public int id;
    public List<TelevisionShowCreditDataModel> cast = null;
    public List<TelevisionShowCreditDataModel> crew = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<TelevisionShowCreditDataModel> getCast() {
        return cast;
    }

    public List<TelevisionShowCreditDataModel> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<TelevisionShowCreditDataModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<TelevisionShowCreditDataModel> crew) {
        this.crew = crew;
    }

    // endregion

    @Override
    public String toString() {
        return "TelevisionShowCreditsDataModel{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

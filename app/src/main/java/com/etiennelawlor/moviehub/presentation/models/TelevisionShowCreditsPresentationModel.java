package com.etiennelawlor.moviehub.presentation.models;

import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditsPresentationModel {

    // region Fields
    public int id;
    public List<TelevisionShowCreditPresentationModel> cast = null;
    public List<TelevisionShowCreditPresentationModel> crew = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<TelevisionShowCreditPresentationModel> getCast() {
        return cast;
    }

    public List<TelevisionShowCreditPresentationModel> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<TelevisionShowCreditPresentationModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<TelevisionShowCreditPresentationModel> crew) {
        this.crew = crew;
    }

    // endregion

    @Override
    public String toString() {
        return "TelevisionShowCreditsPresentationModel{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

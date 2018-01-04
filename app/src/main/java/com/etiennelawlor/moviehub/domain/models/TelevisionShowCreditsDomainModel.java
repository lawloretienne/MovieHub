package com.etiennelawlor.moviehub.domain.models;

import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditsDomainModel {

    // region Fields
    public int id;
    public List<TelevisionShowCreditDomainModel> cast = null;
    public List<TelevisionShowCreditDomainModel> crew = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<TelevisionShowCreditDomainModel> getCast() {
        return cast;
    }

    public List<TelevisionShowCreditDomainModel> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<TelevisionShowCreditDomainModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<TelevisionShowCreditDomainModel> crew) {
        this.crew = crew;
    }

    // endregion

    @Override
    public String toString() {
        return "TelevisionShowCreditsDomainModel{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

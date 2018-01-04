package com.etiennelawlor.moviehub.domain.models;

import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonCreditsDomainModel {

    // region Fields
    public int id;
    public List<PersonCreditDomainModel> cast = null;
    public List<PersonCreditDomainModel> crew = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<PersonCreditDomainModel> getCast() {
        return cast;
    }

    public List<PersonCreditDomainModel> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<PersonCreditDomainModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<PersonCreditDomainModel> crew) {
        this.crew = crew;
    }

    // endregion

    @Override
    public String toString() {
        return "PersonCreditsDomainModel{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

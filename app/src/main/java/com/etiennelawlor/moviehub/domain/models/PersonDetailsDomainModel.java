package com.etiennelawlor.moviehub.domain.models;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class PersonDetailsDomainModel {

    // region Member Variables
    private PersonDomainModel person;
    private List<PersonCreditDomainModel> cast;
    private List<PersonCreditDomainModel> crew;
    // endregion

    // region Constructors
    public PersonDetailsDomainModel() {
    }
    // endregion

    // region Getters

    public PersonDomainModel getPerson() {
        return person;
    }

    public List<PersonCreditDomainModel> getCast() {
        return cast;
    }

    public List<PersonCreditDomainModel> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setPerson(PersonDomainModel person) {
        this.person = person;
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
        return "PersonDetailsDomainModel{" +
                "person=" + person +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

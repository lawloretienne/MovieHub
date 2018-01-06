package com.etiennelawlor.moviehub.presentation.models;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class PersonDetailsPresentationModel {

    // region Fields
    private PersonPresentationModel person;
    private List<PersonCreditPresentationModel> cast;
    private List<PersonCreditPresentationModel> crew;
    // endregion

    // region Constructors
    public PersonDetailsPresentationModel() {
    }
    // endregion

    // region Getters

    public PersonPresentationModel getPerson() {
        return person;
    }

    public List<PersonCreditPresentationModel> getCast() {
        return cast;
    }

    public List<PersonCreditPresentationModel> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setPerson(PersonPresentationModel person) {
        this.person = person;
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
        return "PersonDetailsPresentationModel{" +
                "person=" + person +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}

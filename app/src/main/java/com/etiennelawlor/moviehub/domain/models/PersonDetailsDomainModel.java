package com.etiennelawlor.moviehub.domain.models;

import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class PersonDetailsDomainModel {

    // region Member Variables
    private PersonDataModel person;
    private List<PersonCreditDataModel> cast;
    private List<PersonCreditDataModel> crew;
    // endregion

    // region Constructors

    public PersonDetailsDomainModel(PersonDataModel person, List<PersonCreditDataModel> cast, List<PersonCreditDataModel> crew) {
        this.person = person;
        this.cast = cast;
        this.crew = crew;
    }

    // endregion

    // region Getters

    public PersonDataModel getPerson() {
        return person;
    }

    public List<PersonCreditDataModel> getCast() {
        return cast;
    }

    public List<PersonCreditDataModel> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setPerson(PersonDataModel person) {
        this.person = person;
    }

    public void setCast(List<PersonCreditDataModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<PersonCreditDataModel> crew) {
        this.crew = crew;
    }

    // endregion
}

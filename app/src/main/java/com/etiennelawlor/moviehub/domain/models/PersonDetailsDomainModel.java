package com.etiennelawlor.moviehub.domain.models;

import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditResponse;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class PersonDetailsDomainModel {

    // region Member Variables
    private PersonResponse person;
    private List<PersonCreditResponse> cast;
    private List<PersonCreditResponse> crew;
    // endregion

    // region Constructors

    public PersonDetailsDomainModel(PersonResponse person, List<PersonCreditResponse> cast, List<PersonCreditResponse> crew) {
        this.person = person;
        this.cast = cast;
        this.crew = crew;
    }

    // endregion

    // region Getters

    public PersonResponse getPerson() {
        return person;
    }

    public List<PersonCreditResponse> getCast() {
        return cast;
    }

    public List<PersonCreditResponse> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setPerson(PersonResponse person) {
        this.person = person;
    }

    public void setCast(List<PersonCreditResponse> cast) {
        this.cast = cast;
    }

    public void setCrew(List<PersonCreditResponse> crew) {
        this.crew = crew;
    }

    // endregion
}

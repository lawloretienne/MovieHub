package com.etiennelawlor.moviehub.data.source.person.models;

import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCredit;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class PersonDetailsWrapper {

    // region Member Variables
    private Person person;
    private List<PersonCredit> cast;
    private List<PersonCredit> crew;
    // endregion

    // region Constructors

    public PersonDetailsWrapper(Person person, List<PersonCredit> cast, List<PersonCredit> crew) {
        this.person = person;
        this.cast = cast;
        this.crew = crew;
    }

    // endregion

    // region Getters

    public Person getPerson() {
        return person;
    }

    public List<PersonCredit> getCast() {
        return cast;
    }

    public List<PersonCredit> getCrew() {
        return crew;
    }

    // endregion

    // region Setters

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setCast(List<PersonCredit> cast) {
        this.cast = cast;
    }

    public void setCrew(List<PersonCredit> crew) {
        this.crew = crew;
    }

    // endregion
}

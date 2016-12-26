package com.etiennelawlor.moviehub.models;

import com.etiennelawlor.moviehub.network.models.Person;
import com.etiennelawlor.moviehub.network.models.PersonCreditsEnvelope;

/**
 * Created by etiennelawlor on 12/19/16.
 */

public class FullPerson {

    // region Member Variables
    private Person person;
    private PersonCreditsEnvelope personCreditsEnvelope;
    // endregion

    // region Constructors

    public FullPerson(Person person, PersonCreditsEnvelope personCreditsEnvelope) {
        this.person = person;
        this.personCreditsEnvelope = personCreditsEnvelope;
    }

    // endregion

    // region Getters

    public Person getPerson() {
        return person;
    }

    public PersonCreditsEnvelope getPersonCreditsEnvelope() {
        return personCreditsEnvelope;
    }

    // endregion

    // region Setters

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setPersonCreditsEnvelope(PersonCreditsEnvelope personCreditsEnvelope) {
        this.personCreditsEnvelope = personCreditsEnvelope;
    }

    // endregion
}

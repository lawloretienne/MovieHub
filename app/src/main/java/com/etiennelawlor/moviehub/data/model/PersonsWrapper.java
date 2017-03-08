package com.etiennelawlor.moviehub.data.model;

import com.etiennelawlor.moviehub.data.remote.response.Person;

import java.util.List;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class PersonsWrapper {

    // region Member Variables
    private List<Person> persons;
    private PagingInfo pagingInfo;
    // endregion

    // region Constructors

    public PersonsWrapper(List<Person> persons, PagingInfo pagingInfo) {
        this.persons = persons;
        this.pagingInfo = pagingInfo;
    }

    // endregion

    // region Getters

    public List<Person> getPersons() {
        return persons;
    }

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    // endregion

    // region Setters

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    // endregion

    // Helper Methods
    public boolean hasPersons() { return persons.size() > 0;}
    // endregion
}

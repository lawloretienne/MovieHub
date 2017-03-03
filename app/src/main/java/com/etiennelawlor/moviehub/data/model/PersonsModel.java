package com.etiennelawlor.moviehub.data.model;

import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.Person;

import java.util.List;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class PersonsModel {

    // region Member Variables
    private List<Person> persons;
    private int currentPage;
    private boolean isLastPage;
    // endregion

    // region Constructors

    public PersonsModel(List<Person> persons, int currentPage, boolean isLastPage) {
        this.persons = persons;
        this.currentPage = currentPage;
        this.isLastPage = isLastPage;
    }

    // endregion

    // region Getters


    public List<Person> getPersons() {
        return persons;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    // endregion

    // region Setters


    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    // endregion

    // Helper Methods
    public void incrementPage() { this.currentPage += 1; }

    public boolean hasPersons() { return persons.size() > 0;}
    // endregion
}

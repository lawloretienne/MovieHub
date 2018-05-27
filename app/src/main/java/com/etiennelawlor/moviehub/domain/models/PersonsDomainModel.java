package com.etiennelawlor.moviehub.domain.models;

import java.util.Date;
import java.util.List;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class PersonsDomainModel {

    // region Fields
    private List<PersonDomainModel> persons;
    private int pageNumber;
    private boolean isLastPage;
    private Date expiredAt;
    // endregion

    // region Constructors

    public PersonsDomainModel() {
    }

    // endregion

    // region Getters

    public List<PersonDomainModel> getPersons() {
        return persons;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    // endregion

    // region Setters

    public void setPersons(List<PersonDomainModel> persons) {
        this.persons = persons;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    // endregion

    // region Helper Methods
    public boolean hasPersons() { return persons.size() > 0;}
    // endregion

    @Override
    public String toString() {
        return "PersonsDomainModel{" +
                "persons=" + persons +
                ", pageNumber=" + pageNumber +
                ", isLastPage=" + isLastPage +
                ", expiredAt=" + expiredAt +
                '}';
    }
}

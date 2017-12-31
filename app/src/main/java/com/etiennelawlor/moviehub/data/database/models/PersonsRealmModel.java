package com.etiennelawlor.moviehub.data.database.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class PersonsRealmModel extends RealmObject {

    // region Member Variables
    private RealmList<PersonRealmModel> persons;
    @PrimaryKey
    private int pageNumber;
    private boolean isLastPage;
    private Date expiredAt;
    // endregion

    // region Getters

    public RealmList<PersonRealmModel> getPersons() {
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

    public void setPersons(RealmList<PersonRealmModel> persons) {
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
}

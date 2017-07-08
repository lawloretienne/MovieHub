package com.etiennelawlor.moviehub.data.source.person;

import android.content.Context;

import com.etiennelawlor.moviehub.data.database.RealmUtility;
import com.etiennelawlor.moviehub.data.source.person.models.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.source.person.models.PersonsPage;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonLocalDataSource implements PersonDataSourceContract.LocalDateSource {

    // region Constructors
    public PersonLocalDataSource(Context context) {
    }
    // endregion

    // region PersonDataSourceContract.LocalDateSource Methods

    @Override
    public Observable<PersonsPage> getPopularPersons(int currentPage) {
        PersonsPage personsPage = RealmUtility.getPersonsPage(currentPage);
        if(personsPage == null)
            return Observable.empty();
        else
            return Observable.just(personsPage);
    }

    @Override
    public void savePopularPersons(PersonsPage personsPage) {
        RealmUtility.savePersonsPage(personsPage);
    }

    @Override
    public Observable<PersonDetailsWrapper> getPersonDetails(int personId) {
        //        Use mapper to convert from realm objects to POJOs
        return Observable.empty();
    }

    @Override
    public void savePersonDetails(PersonDetailsWrapper personDetailsWrapper) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}

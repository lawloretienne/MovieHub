package com.etiennelawlor.moviehub.data.source.person;

import android.content.Context;

import com.etiennelawlor.moviehub.data.model.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.model.PersonsPage;
import com.etiennelawlor.moviehub.data.remote.response.Person;

import java.util.List;

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
        //        Use mapper to convert from realm objects to POJOs
        return Observable.empty();
    }

    @Override
    public void savePopularPersons(PersonsPage personsPage) {
//        Use mapper to convert from POJOs to realm objects
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

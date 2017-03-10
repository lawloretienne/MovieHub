package com.etiennelawlor.moviehub.data.source.persondetails;

import android.content.Context;

import com.etiennelawlor.moviehub.data.model.PersonDetailsWrapper;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonDetailsLocalDataSource implements PersonDetailsDataSourceContract.LocalDateSource {

    // region Constructors
    public PersonDetailsLocalDataSource(Context context) {
    }
    // endregion

    // region PersonDetailsDataSourceContract.LocalDateSource Methods

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

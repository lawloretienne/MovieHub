package com.etiennelawlor.moviehub.data.source.persons;

import android.content.Context;

import com.etiennelawlor.moviehub.data.remote.response.Person;

import java.util.List;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonsLocalDataSource implements PersonsDataSourceContract.LocalDateSource {

    // region Constructors
    public PersonsLocalDataSource(Context context) {
    }
    // endregion

    // region PersonsDataSourceContract.LocalDateSource Methods

    @Override
    public Observable<List<Person>> getPopularPersons(int currentPage) {
        //        Use mapper to convert from realm objects to POJOs
        return Observable.empty();
    }

    @Override
    public void savePopularPersons(List<Person> movies) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}

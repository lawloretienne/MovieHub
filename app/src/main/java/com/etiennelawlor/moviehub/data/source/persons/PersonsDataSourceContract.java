package com.etiennelawlor.moviehub.data.source.persons;

import com.etiennelawlor.moviehub.data.model.PersonsModel;
import com.etiennelawlor.moviehub.data.remote.response.Person;

import java.util.List;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface PersonsDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Observable<PersonsModel> getPopularPersons(int currentPage);
    }

    interface LocalDateSource {
        Observable<List<Person>> getPopularPersons(int currentPage);
        void savePopularPersons(List<Person> movies);
    }

    interface RemoteDateSource {
         Observable<List<Person>> getPopularPersons(int currentPage);
    }
}

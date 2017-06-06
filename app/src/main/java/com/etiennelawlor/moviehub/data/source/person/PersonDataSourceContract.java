package com.etiennelawlor.moviehub.data.source.person;

import com.etiennelawlor.moviehub.data.model.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.model.PersonsPage;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface PersonDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Observable<PersonsPage> getPopularPersons(int currentPage);

        Observable<PersonDetailsWrapper> getPersonDetails(int personId);
    }

    interface LocalDateSource {
        Observable<PersonsPage> getPopularPersons(int currentPage);
        void savePopularPersons(PersonsPage personsPage);

        Observable<PersonDetailsWrapper> getPersonDetails(int personId);
        void savePersonDetails(PersonDetailsWrapper personDetailsWrapper);
    }

    interface RemoteDateSource {
         Observable<PersonsPage> getPopularPersons(int currentPage);

         Observable<PersonDetailsWrapper> getPersonDetails(int personId);
    }
}

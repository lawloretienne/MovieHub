package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.repositories.person.models.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;

import io.reactivex.Maybe;
import io.reactivex.Single;
import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface PersonDataSourceContract {

    interface Repository {
        Single<PersonsPage> getPopularPersons(int currentPage);

        Observable<PersonDetailsWrapper> getPersonDetails(int personId);
    }

    interface LocalDateSource {
        Maybe<PersonsPage> getPopularPersons(int currentPage);
        void savePopularPersons(PersonsPage personsPage);

        Observable<PersonDetailsWrapper> getPersonDetails(int personId);
        void savePersonDetails(PersonDetailsWrapper personDetailsWrapper);
    }

    interface RemoteDateSource {
         Single<PersonsPage> getPopularPersons(int currentPage);

         Observable<PersonDetailsWrapper> getPersonDetails(int personId);
    }
}

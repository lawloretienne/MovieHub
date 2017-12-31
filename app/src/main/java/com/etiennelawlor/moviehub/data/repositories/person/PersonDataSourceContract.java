package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface PersonDataSourceContract {

    interface Repository {
        Single<PersonsDataModel> getPopularPersons(int currentPage);
        Single<Person> getPerson(int personId);
        Single<PersonCreditsResponse> getPersonCredits(int personId);
    }

    interface LocalDateSource {
        Maybe<PersonsDataModel> getPopularPersons(int currentPage);
        void savePopularPersons(PersonsDataModel personsDataModel);

        Maybe<Person> getPerson(int personId);
        void savePerson(Person person);

        Maybe<PersonCreditsResponse> getPersonCredits(int personId);
        void savePersonCredits(PersonCreditsResponse personCreditsResponse);
    }

    interface RemoteDateSource {
        Single<PersonsResponse> getPopularPersons(int currentPage);
        Single<Person> getPerson(int personId);
        Single<PersonCreditsResponse> getPersonCredits(int personId);
    }
}

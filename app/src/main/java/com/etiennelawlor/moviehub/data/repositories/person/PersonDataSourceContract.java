package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.PersonsEnvelope;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface PersonDataSourceContract {

    interface Repository {
        Single<PersonsPage> getPopularPersons(int currentPage);
        Single<Person> getPerson(int personId);
        Single<PersonCreditsEnvelope> getPersonCredits(int personId);
    }

    interface LocalDateSource {
        Maybe<PersonsPage> getPopularPersons(int currentPage);
        void savePopularPersons(PersonsPage personsPage);

        Maybe<Person> getPerson(int personId);
        void savePerson(Person person);

        Maybe<PersonCreditsEnvelope> getPersonCredits(int personId);
        void savePersonCredits(PersonCreditsEnvelope personCreditsEnvelope);
    }

    interface RemoteDateSource {
        Single<PersonsEnvelope> getPopularPersons(int currentPage);
        Single<Person> getPerson(int personId);
        Single<PersonCreditsEnvelope> getPersonCredits(int personId);
    }
}

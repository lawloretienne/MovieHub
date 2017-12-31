package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.database.RealmUtility;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditsEnvelope;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;

import io.reactivex.Maybe;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonLocalDataSource implements PersonDataSourceContract.LocalDateSource {

    // region Constructors
    public PersonLocalDataSource() {
    }
    // endregion

    // region PersonDataSourceContract.LocalDateSource Methods

    @Override
    public Maybe<PersonsPage> getPopularPersons(int currentPage) {
        PersonsPage personsPage = RealmUtility.getPersonsPage(currentPage);
        if(personsPage == null)
            return Maybe.empty();
        else
            return Maybe.just(personsPage);
    }

    @Override
    public void savePopularPersons(PersonsPage personsPage) {
        RealmUtility.savePersonsPage(personsPage);
    }

    @Override
    public Maybe<Person> getPerson(int personId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void savePerson(Person person) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<PersonCreditsEnvelope> getPersonCredits(int personId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void savePersonCredits(PersonCreditsEnvelope personCreditsEnvelope) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}

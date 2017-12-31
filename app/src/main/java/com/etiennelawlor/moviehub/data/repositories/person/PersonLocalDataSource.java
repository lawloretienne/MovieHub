package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.database.RealmUtility;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;

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
    public Maybe<PersonsDataModel> getPopularPersons(int currentPage) {
        PersonsDataModel personsDataModel = RealmUtility.getPersonsDataModel(currentPage);
        if(personsDataModel == null)
            return Maybe.empty();
        else
            return Maybe.just(personsDataModel);
    }

    @Override
    public void savePopularPersons(PersonsDataModel personsDataModel) {
        RealmUtility.savePersonsDataModel(personsDataModel);
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
    public Maybe<PersonCreditsResponse> getPersonCredits(int personId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void savePersonCredits(PersonCreditsResponse personCreditsResponse) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}

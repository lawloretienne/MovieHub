package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.database.RealmUtility;
import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;
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
    public Maybe<PersonDataModel> getPerson(long personId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void savePerson(PersonDataModel person) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<PersonCreditsDataModel> getPersonCredits(long personId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void savePersonCredits(PersonCreditsDataModel personCreditsDataModel) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}

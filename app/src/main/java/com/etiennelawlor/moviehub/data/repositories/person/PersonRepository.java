package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.repositories.person.models.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonRepository implements PersonDataSourceContract.Repository {

    // region Member Variables
    private PersonDataSourceContract.LocalDateSource personLocalDataSource;
    private PersonDataSourceContract.RemoteDateSource personRemoteDataSource;
    // endregion

    // region Constructors
    public PersonRepository(PersonDataSourceContract.LocalDateSource personLocalDataSource, PersonDataSourceContract.RemoteDateSource personRemoteDataSource) {
        this.personLocalDataSource = personLocalDataSource;
        this.personRemoteDataSource = personRemoteDataSource;
    }
    // endregion

    // region PersonDataSourceContract.Repository Methods
    @Override
    public Single<PersonsPage> getPopularPersons(final int currentPage) {
        Maybe<PersonsPage> local = personLocalDataSource.getPopularPersons(currentPage)
                .filter(personsPage -> !personsPage.isExpired());
        Single<PersonsPage> remote =
                personRemoteDataSource.getPopularPersons(currentPage)
                        .doOnSuccess(personsPage -> personLocalDataSource.savePopularPersons(personsPage));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<PersonDetailsWrapper> getPersonDetails(int personId) {
        Maybe<PersonDetailsWrapper> local = personLocalDataSource.getPersonDetails(personId);
        Single<PersonDetailsWrapper> remote =
                personRemoteDataSource.getPersonDetails(personId)
                        .doOnSuccess(personDetailsWrapper -> personLocalDataSource.savePersonDetails(personDetailsWrapper));

        return local.switchIfEmpty(remote);
    }
    // endregion
}

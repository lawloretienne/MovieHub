package com.etiennelawlor.moviehub.data.source.person;

import com.etiennelawlor.moviehub.data.model.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.model.PersonsPage;

import rx.Observable;

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
    public Observable<PersonsPage> getPopularPersons(final int currentPage) {
        Observable<PersonsPage> local = personLocalDataSource.getPopularPersons(currentPage)
                .filter(personsPage -> !personsPage.isExpired());
        Observable<PersonsPage> remote =
                personRemoteDataSource.getPopularPersons(currentPage)
                        .doOnNext(personsPage -> personLocalDataSource.savePopularPersons(personsPage));

        return Observable.concat(local, remote).first();
    }

    @Override
    public Observable<PersonDetailsWrapper> getPersonDetails(int personId) {
        Observable<PersonDetailsWrapper> local = personLocalDataSource.getPersonDetails(personId);
        Observable<PersonDetailsWrapper> remote =
                personRemoteDataSource.getPersonDetails(personId)
                        .doOnNext(personDetailsWrapper -> personLocalDataSource.savePersonDetails(personDetailsWrapper));

        return Observable.concat(local, remote).first();
    }
    // endregion
}

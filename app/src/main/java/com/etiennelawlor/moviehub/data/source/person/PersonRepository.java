package com.etiennelawlor.moviehub.data.source.person;

import com.etiennelawlor.moviehub.data.model.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.model.PersonsPage;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonRepository implements PersonDataSourceContract.Repository {

    // Load data from local and remote
    // http://blog.danlew.net/2015/06/22/loading-data-from-multiple-sources-with-rxjava/
//    https://github.com/millionsun93/HackerNews/blob/bd94c62ac658eb3281879c8018540f6dc2c2ec3d/app/src/main/java/com/innovatube/boilerplate/data/HackerNewsRepositoryImpl.java
//    https://github.com/4ndrik/takestock_android/blob/19038a57675cdc88547e9695a81de9269b01dc4e/app/src/main/java/com/devabit/takestock/data/source/DataRepository.java

    // Uses mapper to go from POJO to RealmObject
    // https://github.com/ihorvitruk/buddysearch/blob/master/library/src/main/java/com/buddysearch/android/library/data/mapper/BaseMapper.java
    // https://github.com/dcampogiani/Qwertee/blob/f71dbc318264bcc05a7f51c8cb8c40e54b53b57e/data/src/main/java/com/danielecampogiani/qwertee/data/local/model/MapperImpl.java

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
        Observable<PersonDetailsWrapper> remote = personRemoteDataSource.getPersonDetails(personId);

        return Observable.concat(local, remote)
                .first()
                .doOnNext(personDetailsWrapper -> personLocalDataSource.savePersonDetails(personDetailsWrapper));
    }

    //  Create an Observable that emits a particular item
//  Observable.just(List<Movie> movies)
//  Observable.just(MoviesModel movies)

//  Create an Observable that emits no items but terminates normally
//  Observable.empty();

    // endregion
}

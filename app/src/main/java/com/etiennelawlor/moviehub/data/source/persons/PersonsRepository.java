package com.etiennelawlor.moviehub.data.source.persons;

import com.etiennelawlor.moviehub.data.model.PagingInfo;
import com.etiennelawlor.moviehub.data.model.PersonsWrapper;
import com.etiennelawlor.moviehub.data.remote.response.Person;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonsRepository implements PersonsDataSourceContract.Repository {

    // Load data from local and remote
    // http://blog.danlew.net/2015/06/22/loading-data-from-multiple-sources-with-rxjava/
//    https://github.com/millionsun93/HackerNews/blob/bd94c62ac658eb3281879c8018540f6dc2c2ec3d/app/src/main/java/com/innovatube/boilerplate/data/HackerNewsRepositoryImpl.java
//    https://github.com/4ndrik/takestock_android/blob/19038a57675cdc88547e9695a81de9269b01dc4e/app/src/main/java/com/devabit/takestock/data/source/DataRepository.java

    // Uses mapper to go from POJO to RealmObject
    // https://github.com/ihorvitruk/buddysearch/blob/master/library/src/main/java/com/buddysearch/android/library/data/mapper/BaseMapper.java
    // https://github.com/dcampogiani/Qwertee/blob/f71dbc318264bcc05a7f51c8cb8c40e54b53b57e/data/src/main/java/com/danielecampogiani/qwertee/data/local/model/MapperImpl.java

    // region Constants
    private static final int PAGE_SIZE = 20;
    // endregion

    // region Member Variables
    private PersonsDataSourceContract.LocalDateSource personsLocalDataSource;
    private PersonsDataSourceContract.RemoteDateSource personsRemoteDataSource;
    // endregion

    // region Constructors
    public PersonsRepository(PersonsDataSourceContract.LocalDateSource personsLocalDataSource, PersonsDataSourceContract.RemoteDateSource personsRemoteDataSource) {
        this.personsLocalDataSource = personsLocalDataSource;
        this.personsRemoteDataSource = personsRemoteDataSource;
    }
    // endregion

    // region PersonsDataSourceContract.Repository Methods
    @Override
    public Observable<PersonsWrapper> getPopularPersons(final int currentPage) {
        Observable<List<Person>> local = personsLocalDataSource.getPopularPersons(currentPage);
        Observable<List<Person>> remote = personsRemoteDataSource.getPopularPersons(currentPage);

        return Observable.concat(local, remote)
                .first()
                .map(new Func1<List<Person>, PersonsWrapper>() {
                    @Override
                    public PersonsWrapper call(List<Person> persons) {
                        boolean isLastPage = persons.size() < PAGE_SIZE ? true : false;
                        PagingInfo pagingInfo = new PagingInfo(currentPage, isLastPage);
                        return new PersonsWrapper(persons, pagingInfo);
                    }
                }).doOnNext(new Action1<PersonsWrapper>() {
                    @Override
                    public void call(PersonsWrapper personsWrapper) {
                        List<Person> persons = personsWrapper.getPersons();
                        personsLocalDataSource.savePopularPersons(persons);
                    }
                });
    }

    //  Create an Observable that emits a particular item
//  Observable.just(List<Movie> movies)
//  Observable.just(MoviesModel movies)

//  Create an Observable that emits no items but terminates normally
//  Observable.empty();

    // endregion
}

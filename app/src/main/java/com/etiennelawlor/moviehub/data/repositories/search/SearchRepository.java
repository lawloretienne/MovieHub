package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class SearchRepository implements SearchDataSourceContract.Repository {

    // Load data from local and remote
    // http://blog.danlew.net/2015/06/22/loading-data-from-multiple-sources-with-rxjava/
//    https://github.com/millionsun93/HackerNews/blob/bd94c62ac658eb3281879c8018540f6dc2c2ec3d/app/src/main/java/com/innovatube/boilerplate/data/HackerNewsRepositoryImpl.java
//    https://github.com/4ndrik/takestock_android/blob/19038a57675cdc88547e9695a81de9269b01dc4e/app/src/main/java/com/devabit/takestock/data/source/DataRepository.java

    // Uses mapper to go from POJO to RealmObject
    // https://github.com/ihorvitruk/buddysearch/blob/master/library/src/main/java/com/buddysearch/android/library/data/mapper/BaseMapper.java
    // https://github.com/dcampogiani/Qwertee/blob/f71dbc318264bcc05a7f51c8cb8c40e54b53b57e/data/src/main/java/com/danielecampogiani/qwertee/data/local/model/MapperImpl.java

    // region Member Variables
    private SearchDataSourceContract.LocalDateSource searchLocalDataSource;
    private SearchDataSourceContract.RemoteDateSource searchRemoteDataSource;
    // endregion

    // region Constructors
    public SearchRepository(SearchDataSourceContract.LocalDateSource searchLocalDataSource, SearchDataSourceContract.RemoteDateSource searchRemoteDataSource) {
        this.searchLocalDataSource = searchLocalDataSource;
        this.searchRemoteDataSource = searchRemoteDataSource;
    }
    // endregion

    // region SearchDataSourceContract.Repository Methods
    @Override
    public Observable<SearchWrapper> getSearch(String query) {
        Observable<SearchWrapper> local = searchLocalDataSource.getSearch(query);
        Observable<SearchWrapper> remote = searchRemoteDataSource.getSearch(query);

        return Observable.concat(local, remote)
                .first()
                .doOnNext(new Action1<SearchWrapper>() {
                    @Override
                    public void call(SearchWrapper searchWrapper) {
                        searchLocalDataSource.saveSearch(searchWrapper);
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

package com.etiennelawlor.moviehub.data.source.tv;

import com.etiennelawlor.moviehub.data.model.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.data.model.TelevisionShowsPage;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowRepository implements TelevisionShowDataSourceContract.Repository {

    // Load data from local and remote
    // http://blog.danlew.net/2015/06/22/loading-data-from-multiple-sources-with-rxjava/
//    https://github.com/millionsun93/HackerNews/blob/bd94c62ac658eb3281879c8018540f6dc2c2ec3d/app/src/main/java/com/innovatube/boilerplate/data/HackerNewsRepositoryImpl.java
//    https://github.com/4ndrik/takestock_android/blob/19038a57675cdc88547e9695a81de9269b01dc4e/app/src/main/java/com/devabit/takestock/data/source/DataRepository.java

    // Uses mapper to go from POJO to RealmObject
    // https://github.com/ihorvitruk/buddysearch/blob/master/library/src/main/java/com/buddysearch/android/library/data/mapper/BaseMapper.java
    // https://github.com/dcampogiani/Qwertee/blob/f71dbc318264bcc05a7f51c8cb8c40e54b53b57e/data/src/main/java/com/danielecampogiani/qwertee/data/local/model/MapperImpl.java

    // region Member Variables
    private TelevisionShowDataSourceContract.LocalDateSource televisionShowLocalDataSource;
    private TelevisionShowDataSourceContract.RemoteDateSource televisionShowRemoteDataSource;
    // endregion

    // region Constructors
    public TelevisionShowRepository(TelevisionShowDataSourceContract.LocalDateSource televisionShowLocalDataSource, TelevisionShowDataSourceContract.RemoteDateSource televisionShowRemoteDataSource) {
        this.televisionShowLocalDataSource = televisionShowLocalDataSource;
        this.televisionShowRemoteDataSource = televisionShowRemoteDataSource;
    }
    // endregion

    // region TelevisionShowDataSourceContract.Repository Methods
    @Override
    public Observable<TelevisionShowsPage> getPopularTelevisionShows(final int currentPage) {
        Observable<TelevisionShowsPage> local = televisionShowLocalDataSource.getPopularTelevisionShows(currentPage)
                .filter(televisionShowsPage -> !televisionShowsPage.isExpired());
        Observable<TelevisionShowsPage> remote =
                televisionShowRemoteDataSource.getPopularTelevisionShows(currentPage)
                        .doOnNext(televisionShowsPage -> televisionShowLocalDataSource.savePopularTelevisionShows(televisionShowsPage));

        return Observable.concat(local, remote).first();
    }

    @Override
    public Observable<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int tvId) {
        Observable<TelevisionShowDetailsWrapper> local = televisionShowLocalDataSource.getTelevisionShowDetails(tvId);
        Observable<TelevisionShowDetailsWrapper> remote = televisionShowRemoteDataSource.getTelevisionShowDetails(tvId);

        return Observable.concat(local, remote)
                .first()
                .doOnNext(televisionShowDetailsWrapper -> televisionShowLocalDataSource.saveTelevisionShowDetails(televisionShowDetailsWrapper));
    }

    //  Create an Observable that emits a particular item
//  Observable.just(List<Movie> movies)
//  Observable.just(MoviesModel movies)

//  Create an Observable that emits no items but terminates normally
//  Observable.empty();

    // endregion
}

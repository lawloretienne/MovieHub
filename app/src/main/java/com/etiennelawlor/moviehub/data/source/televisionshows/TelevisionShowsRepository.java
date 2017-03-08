package com.etiennelawlor.moviehub.data.source.televisionshows;

import com.etiennelawlor.moviehub.data.model.PagingInfo;
import com.etiennelawlor.moviehub.data.model.TelevisionShowsWrapper;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowsRepository implements TelevisionShowsDataSourceContract.Repository {

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
    private TelevisionShowsDataSourceContract.LocalDateSource televisionShowsLocalDataSource;
    private TelevisionShowsDataSourceContract.RemoteDateSource televisionShowsRemoteDataSource;
    // endregion

    // region Constructors
    public TelevisionShowsRepository(TelevisionShowsDataSourceContract.LocalDateSource televisionShowsLocalDataSource, TelevisionShowsDataSourceContract.RemoteDateSource televisionShowsRemoteDataSource) {
        this.televisionShowsLocalDataSource = televisionShowsLocalDataSource;
        this.televisionShowsRemoteDataSource = televisionShowsRemoteDataSource;
    }
    // endregion

    // region TelevisionShowsDataSourceContract.Repository Methods
    @Override
    public Observable<TelevisionShowsWrapper> getPopularTelevisionShows(final int currentPage) {
        Observable<List<TelevisionShow>> local = televisionShowsLocalDataSource.getPopularTelevisionShows(currentPage);
        Observable<List<TelevisionShow>> remote = televisionShowsRemoteDataSource.getPopularTelevisionShows(currentPage);

        return Observable.concat(local, remote)
                .first()
                .map(new Func1<List<TelevisionShow>, TelevisionShowsWrapper>() {
                    @Override
                    public TelevisionShowsWrapper call(List<TelevisionShow> televisionShows) {
                        boolean isLastPage = televisionShows.size() < PAGE_SIZE ? true : false;
                        PagingInfo pagingInfo = new PagingInfo(currentPage, isLastPage);
                        return new TelevisionShowsWrapper(televisionShows, pagingInfo);
                    }
                }).doOnNext(new Action1<TelevisionShowsWrapper>() {
                    @Override
                    public void call(TelevisionShowsWrapper televisionShowsWrapper) {
                        List<TelevisionShow> televisionShows = televisionShowsWrapper.getTelevisionShows();
                        televisionShowsLocalDataSource.savePopularTelevisionShows(televisionShows);
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

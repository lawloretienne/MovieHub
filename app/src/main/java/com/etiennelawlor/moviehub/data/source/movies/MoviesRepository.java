package com.etiennelawlor.moviehub.data.source.movies;

import android.util.Log;

import com.etiennelawlor.moviehub.data.model.MoviesPage;
import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.Calendar;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MoviesRepository implements MoviesDataSourceContract.Repository {

    private static final String TAG  = "MoviesRepository";

    // Load data from local and remote
    // http://blog.danlew.net/2015/06/22/loading-data-from-multiple-sources-with-rxjava/
//    https://github.com/millionsun93/HackerNews/blob/bd94c62ac658eb3281879c8018540f6dc2c2ec3d/app/src/main/java/com/innovatube/boilerplate/data/HackerNewsRepositoryImpl.java
//    https://github.com/4ndrik/takestock_android/blob/19038a57675cdc88547e9695a81de9269b01dc4e/app/src/main/java/com/devabit/takestock/data/source/DataRepository.java

    // Uses mapper to go from POJO to RealmObject
    // https://github.com/ihorvitruk/buddysearch/blob/master/library/src/main/java/com/buddysearch/android/library/data/mapper/BaseMapper.java
    // https://github.com/dcampogiani/Qwertee/blob/f71dbc318264bcc05a7f51c8cb8c40e54b53b57e/data/src/main/java/com/danielecampogiani/qwertee/data/local/model/MapperImpl.java

    // region Member Variables
    private MoviesDataSourceContract.LocalDateSource moviesLocalDataSource;
    private MoviesDataSourceContract.RemoteDateSource moviesRemoteDataSource;
    // endregion

    // region Constructors
    public MoviesRepository(MoviesDataSourceContract.LocalDateSource moviesLocalDataSource, MoviesDataSourceContract.RemoteDateSource moviesRemoteDataSource) {
        this.moviesLocalDataSource = moviesLocalDataSource;
        this.moviesRemoteDataSource = moviesRemoteDataSource;
    }
    // endregion

    // region MoviesDataSourceContract.Repository Methods
    @Override
    public Observable<MoviesPage> getPopularMovies(final int currentPage) {
        Observable<MoviesPage> local = moviesLocalDataSource.getPopularMovies(currentPage)
                .filter(new Func1<MoviesPage, Boolean>() {
                    @Override
                    public Boolean call(MoviesPage moviesPage) {
                        Log.d(TAG, "call: moviesPage.isExpired() - "+moviesPage.isExpired());
                        return !moviesPage.isExpired();
                    }
                });
        Observable<MoviesPage> remote =
                moviesRemoteDataSource.getPopularMovies(currentPage)
                        .doOnNext(new Action1<MoviesPage>() {
                            @Override
                            public void call(MoviesPage moviesPage) {
                                moviesLocalDataSource.savePopularMovies(moviesPage);
                            }
                        });

        return Observable.concat(local, remote).first();
    }

//  Create an Observable that emits a particular item
//  Observable.just(List<Movie> movies)
//  Observable.just(MoviesModel movies)

//  Create an Observable that emits no items but terminates normally
//  Observable.empty();

    // endregion
}

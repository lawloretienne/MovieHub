package com.etiennelawlor.moviehub.data.source.movies;

import android.content.Context;

import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MoviesLocalDataSource implements MoviesDataSourceContract.LocalDateSource {

    // region Constructors
    public MoviesLocalDataSource(Context context) {
    }
    // endregion

    // region MoviesDataSourceContract.LocalDateSource Methods

    @Override
    public Observable<List<Movie>> getPopularMovies(int currentPage) {
        //        Use mapper to convert from realm objects to POJOs
        return Observable.empty();
    }

    @Override
    public void savePopularMovies(List<Movie> movies) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //        Use mapper to convert from POJOs to realm objects

            }
        });

    }

    // endregion
}

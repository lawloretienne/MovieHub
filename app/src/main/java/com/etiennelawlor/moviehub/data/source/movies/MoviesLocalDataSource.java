package com.etiennelawlor.moviehub.data.source.movies;

import android.content.Context;

import com.etiennelawlor.moviehub.data.local.realm.RealmUtility;
import com.etiennelawlor.moviehub.data.model.MoviesPage;

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
    public Observable<MoviesPage> getPopularMovies(int currentPage) {
        MoviesPage moviesPage = RealmUtility.getMoviesPage(currentPage);
        if(moviesPage == null)
            return Observable.empty();
        else
            return Observable.just(moviesPage);
    }

    @Override
    public void savePopularMovies(MoviesPage moviesPage) {
        RealmUtility.saveMoviesPage(moviesPage);
    }

    // endregion
}

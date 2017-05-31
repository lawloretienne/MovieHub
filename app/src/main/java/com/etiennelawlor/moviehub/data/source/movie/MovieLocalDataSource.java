package com.etiennelawlor.moviehub.data.source.movie;

import android.content.Context;

import com.etiennelawlor.moviehub.data.local.realm.RealmUtility;
import com.etiennelawlor.moviehub.data.model.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.data.model.MoviesPage;
import com.etiennelawlor.moviehub.data.source.movies.MoviesDataSourceContract;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MovieLocalDataSource implements MovieDataSourceContract.LocalDateSource {

    // region Constructors
    public MovieLocalDataSource(Context context) {
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

    @Override
    public Observable<MovieDetailsWrapper> getMovieDetails(int movieId) {
        //        Use mapper to convert from realm objects to POJOs
        return Observable.empty();
    }

    @Override
    public void saveMovieDetails(MovieDetailsWrapper movieDetailsWrapper) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}

package com.etiennelawlor.moviehub.data.repositories.movie;

import android.content.Context;

import com.etiennelawlor.moviehub.data.database.RealmUtility;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;

import io.reactivex.Maybe;

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
    public Maybe<MoviesPage> getPopularMovies(int currentPage) {
        MoviesPage moviesPage = RealmUtility.getMoviesPage(currentPage);
        if (moviesPage == null)
            return Maybe.empty();
        else
            return Maybe.just(moviesPage);
    }

    @Override
    public void savePopularMovies(MoviesPage moviesPage) {
        RealmUtility.saveMoviesPage(moviesPage);
    }

    @Override
    public Maybe<MovieDetailsWrapper> getMovieDetails(int movieId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveMovieDetails(MovieDetailsWrapper movieDetailsWrapper) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}

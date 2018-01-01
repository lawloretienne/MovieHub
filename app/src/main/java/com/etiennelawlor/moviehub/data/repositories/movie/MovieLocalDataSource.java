package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.database.RealmUtility;
import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDatesDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;

import io.reactivex.Maybe;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MovieLocalDataSource implements MovieDataSourceContract.LocalDateSource {

    // region Constructors
    public MovieLocalDataSource() {
    }
    // endregion

    // region MoviesDataSourceContract.LocalDateSource Methods
    @Override
    public Maybe<MoviesDataModel> getPopularMovies(int currentPage) {
        MoviesDataModel moviesDataModel = RealmUtility.getMoviesDataModel(currentPage);
        if (moviesDataModel == null)
            return Maybe.empty();
        else
            return Maybe.just(moviesDataModel);
    }

    @Override
    public void savePopularMovies(MoviesDataModel moviesDataModel) {
        RealmUtility.saveMoviesDataModel(moviesDataModel);
    }

    @Override
    public Maybe<MovieDataModel> getMovie(int movieId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveMovie(MovieDataModel movieDataModel) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<MovieCreditsDataModel> getMovieCredits(int movieId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveMovieCredits(MovieCreditsDataModel movieCreditsDataModel) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<MoviesDataModel> getSimilarMovies(int movieId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveSimilarMovies(MoviesDataModel moviesDataModel) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<MovieReleaseDatesDataModel> getMovieReleaseDates(int movieId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveMovieReleaseDates(MovieReleaseDatesDataModel movieReleaseDatesDataModel) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}

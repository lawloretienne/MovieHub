package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.database.RealmUtility;
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesDataModel;

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
    public Maybe<Movie> getMovie(int movieId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveMovie(Movie movie) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<MovieCreditsEnvelope> getMovieCredits(int movieId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveMovieCredits(MovieCreditsEnvelope movieCreditsEnvelope) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<MoviesEnvelope> getSimilarMovies(int movieId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveSimilarMovies(MoviesEnvelope movies) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<MovieReleaseDatesEnvelope> getMovieReleaseDates(int movieId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveMovieReleaseDates(MovieReleaseDatesEnvelope movieReleaseDatesEnvelope) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}

package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesDataModel;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MovieDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Single<MoviesDataModel> getPopularMovies(int currentPage);
        Single<Movie> getMovie(int movieId);
        Single<MovieCreditsEnvelope> getMovieCredits(int movieId);
        Single<MoviesEnvelope> getSimilarMovies(int movieId);
        Single<MovieReleaseDatesEnvelope> getMovieReleaseDates(int movieId);
    }

    interface LocalDateSource {
        Maybe<MoviesDataModel> getPopularMovies(int currentPage);
        void savePopularMovies(MoviesDataModel moviesDataModel);

        Maybe<Movie> getMovie(int movieId);
        void saveMovie(Movie movie);

        Maybe<MovieCreditsEnvelope> getMovieCredits(int movieId);
        void saveMovieCredits(MovieCreditsEnvelope movieCreditsEnvelope);

        Maybe<MoviesEnvelope> getSimilarMovies(int movieId);
        void saveSimilarMovies(MoviesEnvelope movies);

        Maybe<MovieReleaseDatesEnvelope> getMovieReleaseDates(int movieId);
        void saveMovieReleaseDates(MovieReleaseDatesEnvelope movieReleaseDatesEnvelope);
    }

    interface RemoteDateSource {
        Single<MoviesEnvelope> getPopularMovies(int currentPage);
        Single<Movie> getMovie(int movieId);
        Single<MovieCreditsEnvelope> getMovieCredits(int movieId);
        Single<MoviesEnvelope> getSimilarMovies(int movieId);
        Single<MovieReleaseDatesEnvelope> getMovieReleaseDates(int movieId);
    }
}

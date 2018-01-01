package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.network.response.MovieCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesResponse;
import com.etiennelawlor.moviehub.data.network.response.MovieResponse;
import com.etiennelawlor.moviehub.data.network.response.MoviesResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDatesDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MovieDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Single<MoviesDataModel> getPopularMovies(int currentPage);
        Single<MovieDataModel> getMovie(int movieId);
        Single<MovieCreditsDataModel> getMovieCredits(int movieId);
        Single<MoviesDataModel> getSimilarMovies(int movieId);
        Single<MovieReleaseDatesDataModel> getMovieReleaseDates(int movieId);
    }

    interface LocalDateSource {
        Maybe<MoviesDataModel> getPopularMovies(int currentPage);
        void savePopularMovies(MoviesDataModel moviesDataModel);

        Maybe<MovieDataModel> getMovie(int movieId);
        void saveMovie(MovieDataModel movie);

        Maybe<MovieCreditsDataModel> getMovieCredits(int movieId);
        void saveMovieCredits(MovieCreditsDataModel movieCreditsDataModel);

        Maybe<MoviesDataModel> getSimilarMovies(int movieId);
        void saveSimilarMovies(MoviesDataModel moviesDataModel);

        Maybe<MovieReleaseDatesDataModel> getMovieReleaseDates(int movieId);
        void saveMovieReleaseDates(MovieReleaseDatesDataModel movieReleaseDatesEnvelope);
    }

    interface RemoteDateSource {
        Single<MoviesResponse> getPopularMovies(int currentPage);
        Single<MovieResponse> getMovie(int movieId);
        Single<MovieCreditsResponse> getMovieCredits(int movieId);
        Single<MoviesResponse> getSimilarMovies(int movieId);
        Single<MovieReleaseDatesResponse> getMovieReleaseDates(int movieId);
    }
}

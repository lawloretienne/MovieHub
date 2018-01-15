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
        Single<MovieDataModel> getMovie(long movieId);
        Single<MovieCreditsDataModel> getMovieCredits(long movieId);
        Single<MoviesDataModel> getSimilarMovies(long movieId);
        Single<MovieReleaseDatesDataModel> getMovieReleaseDates(long movieId);
    }

    interface LocalDateSource {
        Maybe<MoviesDataModel> getPopularMovies(int currentPage);
        void savePopularMovies(MoviesDataModel moviesDataModel);
        Maybe<MovieDataModel> getMovie(long movieId);
        void saveMovie(MovieDataModel movie);
        Maybe<MovieCreditsDataModel> getMovieCredits(long movieId);
        void saveMovieCredits(MovieCreditsDataModel movieCreditsDataModel);
        Maybe<MoviesDataModel> getSimilarMovies(long movieId);
        void saveSimilarMovies(MoviesDataModel moviesDataModel);
        Maybe<MovieReleaseDatesDataModel> getMovieReleaseDates(long movieId);
        void saveMovieReleaseDates(MovieReleaseDatesDataModel movieReleaseDatesEnvelope);
    }

    interface RemoteDateSource {
        Single<MoviesResponse> getPopularMovies(int currentPage);
        Single<MovieResponse> getMovie(long movieId);
        Single<MovieCreditsResponse> getMovieCredits(long movieId);
        Single<MoviesResponse> getSimilarMovies(long movieId);
        Single<MovieReleaseDatesResponse> getMovieReleaseDates(long movieId);
    }
}

package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MovieDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Single<MoviesPage> getPopularMovies(int currentPage);
        Single<MovieDetailsWrapper> getMovieDetails(int movieId);
    }

    interface LocalDateSource {
        Maybe<MoviesPage> getPopularMovies(int currentPage);
        void savePopularMovies(MoviesPage moviesPage);

        Maybe<MovieDetailsWrapper> getMovieDetails(int movieId);
        void saveMovieDetails(MovieDetailsWrapper movieDetailsWrapper);
    }

    interface RemoteDateSource {
        Single<MoviesEnvelope> getPopularMovies(int currentPage);

        Single<MovieDetailsWrapper> getMovieDetails(int movieId);
    }
}

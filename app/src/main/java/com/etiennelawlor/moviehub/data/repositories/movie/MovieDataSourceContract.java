package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;

import io.reactivex.Maybe;
import io.reactivex.Single;
import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MovieDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Single<MoviesPage> getPopularMovies(int currentPage);
        Observable<MovieDetailsWrapper> getMovieDetails(int movieId);
    }

    interface LocalDateSource {
        Maybe<MoviesPage> getPopularMovies(int currentPage);
        void savePopularMovies(MoviesPage moviesPage);

        Observable<MovieDetailsWrapper> getMovieDetails(int movieId);
        void saveMovieDetails(MovieDetailsWrapper movieDetailsWrapper);
    }

    interface RemoteDateSource {
        Single<MoviesPage> getPopularMovies(int currentPage);

        Observable<MovieDetailsWrapper> getMovieDetails(int movieId);
    }
}

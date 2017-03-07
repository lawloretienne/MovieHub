package com.etiennelawlor.moviehub.data.source.movies;

import com.etiennelawlor.moviehub.data.model.MoviesWrapper;
import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.List;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MoviesDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Observable<MoviesWrapper> getPopularMovies(int currentPage);
    }

    interface LocalDateSource {
        Observable<List<Movie>> getPopularMovies(int currentPage);
        void savePopularMovies(List<Movie> movies);
    }

    interface RemoteDateSource {
         Observable<List<Movie>> getPopularMovies(int currentPage);
    }
}

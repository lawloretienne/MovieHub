package com.etiennelawlor.moviehub.data.source.movies;

import com.etiennelawlor.moviehub.data.model.MoviesPage;
import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.List;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MoviesDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Observable<MoviesPage> getPopularMovies(int currentPage);
    }

    interface LocalDateSource {
        Observable<MoviesPage> getPopularMovies(int currentPage);
        void savePopularMovies(MoviesPage moviesWrapper);
    }

    interface RemoteDateSource {
         Observable<MoviesPage> getPopularMovies(int currentPage);
    }
}

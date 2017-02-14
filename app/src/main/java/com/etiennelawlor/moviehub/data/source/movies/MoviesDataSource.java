package com.etiennelawlor.moviehub.data.source.movies;

import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.List;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MoviesDataSource {

    void getMovies(int currentPage, GetMoviesCallback callback);

    interface GetMoviesCallback<R> {
        void onSuccess(R response, int currentPage);
        void onError(Throwable throwable, int currentPage);
    }
}

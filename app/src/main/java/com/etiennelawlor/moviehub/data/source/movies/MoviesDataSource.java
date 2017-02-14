package com.etiennelawlor.moviehub.data.source.movies;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MoviesDataSource {

    void getMovies(int currentPage, GetMoviesCallback<?> callback);
    void getMoviesFirstPage(GetMoviesCallback<?> callback);
    void getMoviesNextPage(GetMoviesCallback<?> callback);

    interface GetMoviesCallback<R> {
        void onSuccess(R response, int currentPage);
        void onError(Throwable throwable, int currentPage);
    }
}

package com.etiennelawlor.moviehub.data.source.movies;

import android.content.Context;

import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.List;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MoviesLocalDataSource implements MoviesDataSource {

    // region Constructors
    public MoviesLocalDataSource(Context context) {
    }
    // endregion

    // region MoviesDataSource Methods
//    @Override
//    public Observable<List<Movie>> getMovies(int currentPage) {
//        return null;
//    }

    @Override
    public void getMovies(int currentPage, GetMoviesCallback callback) {

    }

    // endregion
}

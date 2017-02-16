package com.etiennelawlor.moviehub.data.source.movies;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MoviesRepository implements MoviesDataSource {

    // region Member Variables
    private MoviesDataSource moviesLocalDataSource;
    private MoviesDataSource moviesRemoteDataSource;
    private int currentPage = 0;
    // endregion

    // region Constructors
    public MoviesRepository(MoviesDataSource moviesLocalDataSource, MoviesDataSource moviesRemoteDataSource) {
        this.moviesLocalDataSource = moviesLocalDataSource;
        this.moviesRemoteDataSource = moviesRemoteDataSource;
    }
    // endregion

    // region MoviesDataSource Methods
    @Override
    public Observable getMovies(int currentPage) {
        return moviesRemoteDataSource.getMovies(currentPage);
    }
    // endregion
}

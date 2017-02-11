package com.etiennelawlor.moviehub.data.repository;

import com.etiennelawlor.moviehub.data.remote.MovieHubService;

import retrofit2.Call;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesRemoteRepository implements MoviesRepository {

    private MovieHubService service;

    // region Constructors
    public MoviesRemoteRepository(MovieHubService service){
        this.service = service;
    }
    // endregion

    @Override
    public Call getPopularMovies(int currentPage) {
        return service.getPopularMovies(currentPage);
    }
}

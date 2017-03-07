package com.etiennelawlor.moviehub.data.model;

import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.List;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class MoviesWrapper {

    // region Member Variables
    private List<Movie> movies;
    private PagingInfo pagingInfo;
    // endregion

    // region Constructors

    public MoviesWrapper(List<Movie> movies, PagingInfo pagingInfo) {
        this.movies = movies;
        this.pagingInfo = pagingInfo;
    }

    // endregion

    // region Getters

    public List<Movie> getMovies() {
        return movies;
    }

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    // endregion

    // region Setters

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    // endregion

    // Helper Methods
    public boolean hasMovies() { return movies.size() > 0;}
    // endregion
}

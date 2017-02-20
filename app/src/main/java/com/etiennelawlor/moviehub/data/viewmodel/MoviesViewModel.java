package com.etiennelawlor.moviehub.data.viewmodel;

import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.List;

/**
 * Created by etiennelawlor on 2/15/17.
 */

public class MoviesViewModel {

    // region Member Variables
    private List<Movie> movies;
    private int currentPage;
    private boolean isLastPage;
    // endregion

    // region Constructors

    public MoviesViewModel(List<Movie> movies, int currentPage, boolean isLastPage) {
        this.movies = movies;
        this.currentPage = currentPage;
        this.isLastPage = isLastPage;
    }

    // endregion

    // region Getters

    public List<Movie> getMovies() {
        return movies;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    // endregion

    // region Setters

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    // endregion

    // Helper Methods
    public void incrementPage() { this.currentPage += 1; }
    // endregion

}

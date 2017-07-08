package com.etiennelawlor.moviehub.data.source.movie.models;

import com.etiennelawlor.moviehub.data.network.response.Movie;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class MoviesPage {

    // region Member Variables
    private List<Movie> movies;
    private int pageNumber;
    private boolean isLastPage;
    private Date expiredAt;
    // endregion

    // region Constructors

    public MoviesPage(List<Movie> movies, int pageNumber, boolean isLastPage, Date expiredAt) {
        this.movies = movies;
        this.pageNumber = pageNumber;
        this.isLastPage = isLastPage;
        this.expiredAt = expiredAt;
    }

    public MoviesPage() {
    }

    // endregion

    // region Getters

    public List<Movie> getMovies() {
        return movies;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    // endregion

    // region Setters

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    // endregion

    // Helper Methods
    public boolean hasMovies() { return movies.size() > 0;}

    public void incrementPageNumber() { this.pageNumber += 1; }

    public boolean isExpired() {
        return Calendar.getInstance().getTime().getTime() > expiredAt.getTime();
    }
    // endregion
}

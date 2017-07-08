package com.etiennelawlor.moviehub.data.model;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MovieCredit;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class MovieDetailsWrapper {

    // region Member Variables
    private Movie movie;
    private List<MovieCredit> cast;
    private List<MovieCredit> crew;
    private List<Movie> similarMovies;
    private String rating;
    // endregion

    // region Constructors

    public MovieDetailsWrapper(Movie movie, List<MovieCredit> cast, List<MovieCredit> crew, List<Movie> similarMovies, String rating) {
        this.movie = movie;
        this.cast = cast;
        this.crew = crew;
        this.similarMovies = similarMovies;
        this.rating = rating;
    }

    // endregion

    // region Getters

    public Movie getMovie() {
        return movie;
    }

    public List<MovieCredit> getCast() {
        return cast;
    }

    public List<MovieCredit> getCrew() {
        return crew;
    }

    public List<Movie> getSimilarMovies() {
        return similarMovies;
    }

    public String getRating() {
        return rating;
    }

    // endregion

    // region Setters

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setCast(List<MovieCredit> cast) {
        this.cast = cast;
    }

    public void setCrew(List<MovieCredit> crew) {
        this.crew = crew;
    }

    public void setSimilarMovies(List<Movie> similarMovies) {
        this.similarMovies = similarMovies;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // endregion
}

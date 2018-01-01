package com.etiennelawlor.moviehub.domain.models;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MovieCreditResponse;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class MovieDetailsDomainModel {

    // region Member Variables
    private Movie movie;
    private List<MovieCreditResponse> cast;
    private List<MovieCreditResponse> crew;
    private List<Movie> similarMovies;
    private String rating;
    // endregion

    // region Constructors

    public MovieDetailsDomainModel(Movie movie, List<MovieCreditResponse> cast, List<MovieCreditResponse> crew, List<Movie> similarMovies, String rating) {
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

    public List<MovieCreditResponse> getCast() {
        return cast;
    }

    public List<MovieCreditResponse> getCrew() {
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

    public void setCast(List<MovieCreditResponse> cast) {
        this.cast = cast;
    }

    public void setCrew(List<MovieCreditResponse> crew) {
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

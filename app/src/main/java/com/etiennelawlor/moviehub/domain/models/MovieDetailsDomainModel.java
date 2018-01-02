package com.etiennelawlor.moviehub.domain.models;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class MovieDetailsDomainModel {

    // region Member Variables
    private MovieDomainModel movie;
    private List<MovieCreditDomainModel> cast;
    private List<MovieCreditDomainModel> crew;
    private List<MovieDomainModel> similarMovies;
    private String rating;
    // endregion

    // region Constructors
    public MovieDetailsDomainModel() {
    }
    // endregion

    // region Getters

    public MovieDomainModel getMovie() {
        return movie;
    }

    public List<MovieCreditDomainModel> getCast() {
        return cast;
    }

    public List<MovieCreditDomainModel> getCrew() {
        return crew;
    }

    public List<MovieDomainModel> getSimilarMovies() {
        return similarMovies;
    }

    public String getRating() {
        return rating;
    }

    // endregion

    // region Setters

    public void setMovie(MovieDomainModel movie) {
        this.movie = movie;
    }

    public void setCast(List<MovieCreditDomainModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<MovieCreditDomainModel> crew) {
        this.crew = crew;
    }

    public void setSimilarMovies(List<MovieDomainModel> similarMovies) {
        this.similarMovies = similarMovies;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // endregion


    @Override
    public String toString() {
        return "MovieDetailsPresentationModel{" +
                "movie=" + movie +
                ", cast=" + cast +
                ", crew=" + crew +
                ", similarMovies=" + similarMovies +
                ", rating='" + rating + '\'' +
                '}';
    }
}

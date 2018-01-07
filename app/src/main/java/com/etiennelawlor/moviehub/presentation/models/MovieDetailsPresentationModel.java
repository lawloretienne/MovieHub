package com.etiennelawlor.moviehub.presentation.models;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class MovieDetailsPresentationModel {

    // region Fields
    private MoviePresentationModel movie;
    private List<MovieCreditPresentationModel> cast;
    private List<MovieCreditPresentationModel> crew;
    private List<MoviePresentationModel> similarMovies;
    private String rating;
    // endregion

    // region Constructors
    public MovieDetailsPresentationModel() {
    }
    // endregion

    // region Getters

    public MoviePresentationModel getMovie() {
        return movie;
    }

    public List<MovieCreditPresentationModel> getCast() {
        return cast;
    }

    public List<MovieCreditPresentationModel> getCrew() {
        return crew;
    }

    public List<MoviePresentationModel> getSimilarMovies() {
        return similarMovies;
    }

    public String getRating() {
        return rating;
    }

    // endregion

    // region Setters

    public void setMovie(MoviePresentationModel movie) {
        this.movie = movie;
    }

    public void setCast(List<MovieCreditPresentationModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<MovieCreditPresentationModel> crew) {
        this.crew = crew;
    }

    public void setSimilarMovies(List<MoviePresentationModel> similarMovies) {
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

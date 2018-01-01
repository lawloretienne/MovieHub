package com.etiennelawlor.moviehub.domain.models;

import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class MovieDetailsDomainModel {

    // region Member Variables
    private MovieDataModel movie;
    private List<MovieCreditDataModel> cast;
    private List<MovieCreditDataModel> crew;
    private List<MovieDataModel> similarMovies;
    private String rating;
    // endregion

    // region Constructors
    public MovieDetailsDomainModel() {
    }
    // endregion

    // region Getters

    public MovieDataModel getMovie() {
        return movie;
    }

    public List<MovieCreditDataModel> getCast() {
        return cast;
    }

    public List<MovieCreditDataModel> getCrew() {
        return crew;
    }

    public List<MovieDataModel> getSimilarMovies() {
        return similarMovies;
    }

    public String getRating() {
        return rating;
    }

    // endregion

    // region Setters

    public void setMovie(MovieDataModel movie) {
        this.movie = movie;
    }

    public void setCast(List<MovieCreditDataModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<MovieCreditDataModel> crew) {
        this.crew = crew;
    }

    public void setSimilarMovies(List<MovieDataModel> similarMovies) {
        this.similarMovies = similarMovies;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // endregion
}

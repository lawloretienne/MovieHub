package com.etiennelawlor.moviehub.domain.models;

import com.etiennelawlor.moviehub.data.network.response.MovieCreditResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class MovieDetailsDomainModel {

    // region Member Variables
    private MovieDataModel movie;
    private List<MovieCreditResponse> cast;
    private List<MovieCreditResponse> crew;
    private List<MovieDataModel> similarMovies;
    private String rating;
    // endregion

    // region Constructors

    public MovieDetailsDomainModel(MovieDataModel movie, List<MovieCreditResponse> cast, List<MovieCreditResponse> crew, List<MovieDataModel> similarMovies, String rating) {
        this.movie = movie;
        this.cast = cast;
        this.crew = crew;
        this.similarMovies = similarMovies;
        this.rating = rating;
    }

    // endregion

    // region Getters

    public MovieDataModel getMovie() {
        return movie;
    }

    public List<MovieCreditResponse> getCast() {
        return cast;
    }

    public List<MovieCreditResponse> getCrew() {
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

    public void setCast(List<MovieCreditResponse> cast) {
        this.cast = cast;
    }

    public void setCrew(List<MovieCreditResponse> crew) {
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

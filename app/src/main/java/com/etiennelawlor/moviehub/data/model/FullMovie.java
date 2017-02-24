package com.etiennelawlor.moviehub.data.model;

import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.MovieReleaseDatesEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;

/**
 * Created by etiennelawlor on 12/19/16.
 */

public class FullMovie {

    // region Member Variables
    private Movie movie;
    private MovieCreditsEnvelope movieCreditsEnvelope;
    private MoviesEnvelope moviesEnvelope;
    private MovieReleaseDatesEnvelope movieReleaseDatesEnvelope;
    // endregion

    // region Constructors

    public FullMovie(Movie movie, MovieCreditsEnvelope movieCreditsEnvelope, MoviesEnvelope moviesEnvelope, MovieReleaseDatesEnvelope movieReleaseDatesEnvelope) {
        this.movie = movie;
        this.movieCreditsEnvelope = movieCreditsEnvelope;
        this.moviesEnvelope = moviesEnvelope;
        this.movieReleaseDatesEnvelope = movieReleaseDatesEnvelope;
    }

    // endregion

    // region Getters

    public Movie getMovie() {
        return movie;
    }

    public MovieCreditsEnvelope getMovieCreditsEnvelope() {
        return movieCreditsEnvelope;
    }

    public MoviesEnvelope getMoviesEnvelope() {
        return moviesEnvelope;
    }

    public MovieReleaseDatesEnvelope getMovieReleaseDatesEnvelope() {
        return movieReleaseDatesEnvelope;
    }

    // endregion

    // region Setters

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setMovieCreditsEnvelope(MovieCreditsEnvelope movieCreditsEnvelope) {
        this.movieCreditsEnvelope = movieCreditsEnvelope;
    }

    public void setMoviesEnvelope(MoviesEnvelope moviesEnvelope) {
        this.moviesEnvelope = moviesEnvelope;
    }

    public void setMovieReleaseDatesEnvelope(MovieReleaseDatesEnvelope movieReleaseDatesEnvelope) {
        this.movieReleaseDatesEnvelope = movieReleaseDatesEnvelope;
    }

    // endregion
}

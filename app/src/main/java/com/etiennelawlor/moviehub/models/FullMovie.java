package com.etiennelawlor.moviehub.models;

import com.etiennelawlor.moviehub.network.models.Movie;
import com.etiennelawlor.moviehub.network.models.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.network.models.MoviesEnvelope;

/**
 * Created by etiennelawlor on 12/19/16.
 */

public class FullMovie {

    // region Member Variables
    private Movie movie;
    private MovieCreditsEnvelope movieCreditsEnvelope;
    private MoviesEnvelope moviesEnvelope;
    // endregion

    // region Constructors

    public FullMovie(Movie movie, MovieCreditsEnvelope movieCreditsEnvelope, MoviesEnvelope moviesEnvelope) {
        this.movie = movie;
        this.movieCreditsEnvelope = movieCreditsEnvelope;
        this.moviesEnvelope = moviesEnvelope;
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

    // endregion
}

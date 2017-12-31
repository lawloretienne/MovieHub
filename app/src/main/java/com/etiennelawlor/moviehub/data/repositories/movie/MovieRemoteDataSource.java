package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MovieRemoteDataSource implements MovieDataSourceContract.RemoteDateSource {

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    @Inject
    public MovieRemoteDataSource(MovieHubService movieHubService) {
        this.movieHubService = movieHubService;
    }
    // endregion

    // region MovieDataSourceContract.RemoteDateSource Methods
    @Override
    public Single<MoviesEnvelope> getPopularMovies(final int currentPage) {
        return movieHubService.getPopularMovies(currentPage);
    }

    @Override
    public Single<Movie> getMovie(int movieId) {
        return movieHubService.getMovie(movieId);
    }

    @Override
    public Single<MovieCreditsEnvelope> getMovieCredits(int movieId) {
        return movieHubService.getMovieCredits(movieId);
    }

    @Override
    public Single<MoviesEnvelope> getSimilarMovies(int movieId) {
        return movieHubService.getSimilarMovies(movieId);
    }

    @Override
    public Single<MovieReleaseDatesEnvelope> getMovieReleaseDates(int movieId) {
        return movieHubService.getMovieReleaseDates(movieId);
    }

    // endregion
}

package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.response.MovieCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesResponse;
import com.etiennelawlor.moviehub.data.network.response.MovieResponse;
import com.etiennelawlor.moviehub.data.network.response.MoviesResponse;

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
    public Single<MoviesResponse> getPopularMovies(final int currentPage) {
        return movieHubService.getPopularMovies(currentPage);
    }

    @Override
    public Single<MovieResponse> getMovie(long movieId) {
        return movieHubService.getMovie(movieId);
    }

    @Override
    public Single<MovieCreditsResponse> getMovieCredits(long movieId) {
        return movieHubService.getMovieCredits(movieId);
    }

    @Override
    public Single<MoviesResponse> getSimilarMovies(long movieId) {
        return movieHubService.getSimilarMovies(movieId);
    }

    @Override
    public Single<MovieReleaseDatesResponse> getMovieReleaseDates(long movieId) {
        return movieHubService.getMovieReleaseDates(movieId);
    }

    // endregion
}

package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.movie.MovieDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class MovieDetailsUseCase implements MovieDetailsDomainContract.UseCase {

    // region Member Variables
    private final MovieDataSourceContract.Repository movieRepository;
    // endregion

    // region Constructors
    public MovieDetailsUseCase(MovieDataSourceContract.Repository movieRepository) {
        this.movieRepository = movieRepository;
    }
    // endregion

    // region MovieDetailsDomainContract.UseCase Methods
    @Override
    public Single<MovieDetailsWrapper> getMovieDetails(int movieId) {
        return movieRepository.getMovieDetails(movieId);
    }
    // endregion

}

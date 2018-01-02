package com.etiennelawlor.moviehub.domain.usecases;

import com.etiennelawlor.moviehub.data.repositories.movie.MovieDataSourceContract;
import com.etiennelawlor.moviehub.domain.composers.MovieDetailsDomainModelComposer;
import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class MovieDetailsUseCase implements MovieDetailsDomainContract.UseCase {

    // region Member Variables
    private final MovieDataSourceContract.Repository movieRepository;
    private final MovieDetailsDomainModelComposer movieDetailsDomainModelComposer = new MovieDetailsDomainModelComposer();
    // endregion

    // region Constructors
    public MovieDetailsUseCase(MovieDataSourceContract.Repository movieRepository) {
        this.movieRepository = movieRepository;
    }
    // endregion

    // region MovieDetailsDomainContract.UseCase Methods
    @Override
    public Single<MovieDetailsDomainModel> getMovieDetails(int movieId) {
        return Single.zip(
                movieRepository.getMovie(movieId),
                movieRepository.getMovieCredits(movieId),
                movieRepository.getSimilarMovies(movieId),
                movieRepository.getMovieReleaseDates(movieId),
                (movieDataModel, movieCreditsDataModel, moviesDataModel, movieReleaseDatesDataModel) ->
                    movieDetailsDomainModelComposer.compose(movieDataModel, movieCreditsDataModel, moviesDataModel, movieReleaseDatesDataModel)
                );
    }
    // endregion

}

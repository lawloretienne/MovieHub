package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.repositories.mappers.MoviesDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MovieRepository implements MovieDataSourceContract.Repository {

    // region Member Variables
    private MovieDataSourceContract.LocalDateSource movieLocalDataSource;
    private MovieDataSourceContract.RemoteDateSource movieRemoteDataSource;
    private MoviesDataModelMapper moviesDataModelMapper = new MoviesDataModelMapper();
    // endregion

    // region Constructors
    public MovieRepository(MovieDataSourceContract.LocalDateSource movieLocalDataSource, MovieDataSourceContract.RemoteDateSource movieRemoteDataSource) {
        this.movieLocalDataSource = movieLocalDataSource;
        this.movieRemoteDataSource = movieRemoteDataSource;
    }
    // endregion

    // region MovieDataSourceContract.Repository Methods
    @Override
    public Single<MoviesDataModel> getPopularMovies(final int currentPage) {
        Maybe<MoviesDataModel> local = movieLocalDataSource.getPopularMovies(currentPage)
                .filter(moviesDataModel -> !moviesDataModel.isExpired());
        Single<MoviesDataModel> remote =
                movieRemoteDataSource.getPopularMovies(currentPage)
                        .map(moviesEnvelope -> moviesDataModelMapper.mapToDataModel(moviesEnvelope))
                        .doOnSuccess(moviesDataModel -> movieLocalDataSource.savePopularMovies(moviesDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<Movie> getMovie(int movieId) {
        Maybe<Movie> local = movieLocalDataSource.getMovie(movieId);
        Single<Movie> remote =
                movieRemoteDataSource.getMovie(movieId)
                        .doOnSuccess(movie -> movieLocalDataSource.saveMovie(movie));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<MovieCreditsEnvelope> getMovieCredits(int movieId) {
        Maybe<MovieCreditsEnvelope> local = movieLocalDataSource.getMovieCredits(movieId);
        Single<MovieCreditsEnvelope> remote =
                movieRemoteDataSource.getMovieCredits(movieId)
                        .doOnSuccess(movieCreditsEnvelope -> movieLocalDataSource.saveMovieCredits(movieCreditsEnvelope));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<MoviesEnvelope> getSimilarMovies(int movieId) {
        Maybe<MoviesEnvelope> local = movieLocalDataSource.getSimilarMovies(movieId);
        Single<MoviesEnvelope> remote =
                movieRemoteDataSource.getSimilarMovies(movieId)
                        .doOnSuccess(moviesEnvelope -> movieLocalDataSource.saveSimilarMovies(moviesEnvelope));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<MovieReleaseDatesEnvelope> getMovieReleaseDates(int movieId) {
        Maybe<MovieReleaseDatesEnvelope> local = movieLocalDataSource.getMovieReleaseDates(movieId);
        Single<MovieReleaseDatesEnvelope> remote =
                movieRemoteDataSource.getMovieReleaseDates(movieId)
                        .doOnSuccess(movieReleaseDatesEnvelope -> movieLocalDataSource.saveMovieReleaseDates(movieReleaseDatesEnvelope));

        return local.switchIfEmpty(remote);
    }

    // endregion
}

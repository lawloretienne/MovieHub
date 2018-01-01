package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.repositories.mappers.MovieCreditsDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.MovieDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.MovieReleaseDatesDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.MoviesDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDatesDataModel;
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
    private MovieDataModelMapper movieDataModelMapper = new MovieDataModelMapper();
    private MoviesDataModelMapper moviesDataModelMapper = new MoviesDataModelMapper();
    private MovieCreditsDataModelMapper movieCreditsDataModelMapper = new MovieCreditsDataModelMapper();
    private MovieReleaseDatesDataModelMapper movieReleaseDatesDataModelMapper = new MovieReleaseDatesDataModelMapper();
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
                        .map(moviesResponse -> moviesDataModelMapper.mapToDataModel(moviesResponse))
                        .doOnSuccess(moviesDataModel -> movieLocalDataSource.savePopularMovies(moviesDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<MovieDataModel> getMovie(int movieId) {
        Maybe<MovieDataModel> local = movieLocalDataSource.getMovie(movieId);
        Single<MovieDataModel> remote =
                movieRemoteDataSource.getMovie(movieId)
                        .map(movieResponse -> movieDataModelMapper.mapToDataModel(movieResponse))
                        .doOnSuccess(movie -> movieLocalDataSource.saveMovie(movie));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<MovieCreditsDataModel> getMovieCredits(int movieId) {
        Maybe<MovieCreditsDataModel> local = movieLocalDataSource.getMovieCredits(movieId);
        Single<MovieCreditsDataModel> remote =
                movieRemoteDataSource.getMovieCredits(movieId)
                        .map(movieCreditsResponse -> movieCreditsDataModelMapper.mapToDataModel(movieCreditsResponse))
                        .doOnSuccess(movieCreditsDataModel -> movieLocalDataSource.saveMovieCredits(movieCreditsDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<MoviesDataModel> getSimilarMovies(int movieId) {
        Maybe<MoviesDataModel> local = movieLocalDataSource.getSimilarMovies(movieId);
        Single<MoviesDataModel> remote =
                movieRemoteDataSource.getSimilarMovies(movieId)
                        .map(moviesResponse -> moviesDataModelMapper.mapToDataModel(moviesResponse))
                        .doOnSuccess(moviesDataModel -> movieLocalDataSource.saveSimilarMovies(moviesDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<MovieReleaseDatesDataModel> getMovieReleaseDates(int movieId) {
        Maybe<MovieReleaseDatesDataModel> local = movieLocalDataSource.getMovieReleaseDates(movieId);
        Single<MovieReleaseDatesDataModel> remote =
                movieRemoteDataSource.getMovieReleaseDates(movieId)
                        .map(movieReleaseDatesResponse -> movieReleaseDatesDataModelMapper.mapToDataModel(movieReleaseDatesResponse))
                        .doOnSuccess(movieReleaseDatesDataModel -> movieLocalDataSource.saveMovieReleaseDates(movieReleaseDatesDataModel));

        return local.switchIfEmpty(remote);
    }

    // endregion
}

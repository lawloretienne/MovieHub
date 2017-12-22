package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MovieRepository implements MovieDataSourceContract.Repository {

    // region Member Variables
    private MovieDataSourceContract.LocalDateSource movieLocalDataSource;
    private MovieDataSourceContract.RemoteDateSource movieRemoteDataSource;
    // endregion

    // region Constructors
    public MovieRepository(MovieDataSourceContract.LocalDateSource movieLocalDataSource, MovieDataSourceContract.RemoteDateSource movieRemoteDataSource) {
        this.movieLocalDataSource = movieLocalDataSource;
        this.movieRemoteDataSource = movieRemoteDataSource;
    }
    // endregion

    // region MovieDataSourceContract.Repository Methods
    @Override
    public Single<MoviesPage> getPopularMovies(final int currentPage) {
        Maybe<MoviesPage> local = movieLocalDataSource.getPopularMovies(currentPage)
                .filter(moviesPage -> !moviesPage.isExpired());
        Single<MoviesPage> remote =
                movieRemoteDataSource.getPopularMovies(currentPage)
                        .doOnSuccess(moviesPage -> movieLocalDataSource.savePopularMovies(moviesPage));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<MovieDetailsWrapper> getMovieDetails(int movieId) {
        Maybe<MovieDetailsWrapper> local = movieLocalDataSource.getMovieDetails(movieId);

        Single<MovieDetailsWrapper> remote =
                movieRemoteDataSource.getMovieDetails(movieId)
                        .doOnSuccess(movieDetailsWrapper -> movieLocalDataSource.saveMovieDetails(movieDetailsWrapper));

        return local.switchIfEmpty(remote);
    }

    // endregion
}

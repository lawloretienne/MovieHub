package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;

import java.util.Calendar;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MovieRepository implements MovieDataSourceContract.Repository {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

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
                        .flatMap(moviesEnvelope -> Single.just(moviesEnvelope.getMovies()))
                        .map(movies -> {
                            boolean isLastPage = movies.size() < PAGE_SIZE ? true : false;
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.DATE, SEVEN_DAYS);
                            return new MoviesPage(movies, currentPage, isLastPage, calendar.getTime());
                        })
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

    // Persist MovieDetailsResponse not wrapper

    // endregion
}

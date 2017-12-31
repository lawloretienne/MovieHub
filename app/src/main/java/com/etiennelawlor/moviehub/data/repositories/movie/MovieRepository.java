package com.etiennelawlor.moviehub.data.repositories.movie;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;

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
    public Single<MoviesDataModel> getPopularMovies(final int currentPage) {
        Maybe<MoviesDataModel> local = movieLocalDataSource.getPopularMovies(currentPage)
                .filter(moviesDataModel -> !moviesDataModel.isExpired());
        Single<MoviesDataModel> remote =
                movieRemoteDataSource.getPopularMovies(currentPage)
                        .flatMap(moviesEnvelope -> Single.just(moviesEnvelope.getMovies()))
                        .map(movies -> {
                            boolean isLastPage = movies.size() < PAGE_SIZE ? true : false;
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.DATE, SEVEN_DAYS);
                            return new MoviesDataModel(movies, currentPage, isLastPage, calendar.getTime());
                        })
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

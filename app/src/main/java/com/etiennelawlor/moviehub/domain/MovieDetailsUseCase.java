package com.etiennelawlor.moviehub.domain;

import android.text.TextUtils;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MovieCredit;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDate;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDateResponse;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieDataSourceContract;
import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class MovieDetailsUseCase implements MovieDetailsDomainContract.UseCase {

    // region Constants
    private static final String ISO_31661 = "US";
    // endregion

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
    public Single<MovieDetailsDomainModel> getMovieDetails(int movieId) {
        return Single.zip(
                movieRepository.getMovie(movieId),
                movieRepository.getMovieCredits(movieId),
                movieRepository.getSimilarMovies(movieId),
                movieRepository.getMovieReleaseDates(movieId),
                (movie, movieCreditsDataModel, moviesDataModel, movieReleaseDatesDataModel) -> {
                    List<MovieCredit> cast = new ArrayList<>();
                    List<MovieCredit> crew = new ArrayList<>();
                    List<Movie> similarMovies = new ArrayList<>();
                    String rating = "";

                    if (movieCreditsDataModel != null) {
                        cast = movieCreditsDataModel.getCast();
                    }

                    if (movieCreditsDataModel != null) {
                        crew = movieCreditsDataModel.getCrew();
                    }

                    if (moviesDataModel != null) {
                        similarMovies = moviesDataModel.getMovies();
                    }

                    if (movieReleaseDatesDataModel != null) {
                        List<MovieReleaseDateResponse> movieReleaseDateResponses = movieReleaseDatesDataModel.getMovieReleaseDateResponses();
                        if (movieReleaseDateResponses != null && movieReleaseDateResponses.size() > 0) {
                            for (MovieReleaseDateResponse movieReleaseDateResponse : movieReleaseDateResponses) {
                                if (movieReleaseDateResponse != null) {
                                    String iso31661 = movieReleaseDateResponse.getIso31661();
                                    if (iso31661.equals(ISO_31661)) {
                                        List<MovieReleaseDate> movieReleaseDates = movieReleaseDateResponse.getMovieReleaseDates();
                                        if (movieReleaseDates != null && movieReleaseDates.size() > 0) {
                                            for (MovieReleaseDate movieReleaseDate : movieReleaseDates) {
                                                if (!TextUtils.isEmpty(movieReleaseDate.getCertification())) {
                                                    rating = movieReleaseDate.getCertification();
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    return new MovieDetailsDomainModel(movie, cast, crew, similarMovies, rating);
                });
    }
    // endregion

}

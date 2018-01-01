package com.etiennelawlor.moviehub.domain;

import android.text.TextUtils;

import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDateDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.ReleaseDateDataModel;
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
                (movieDataModel, movieCreditsDataModel, moviesDataModel, movieReleaseDatesDataModel) -> {
                    List<MovieCreditDataModel> cast = new ArrayList<>();
                    List<MovieCreditDataModel> crew = new ArrayList<>();
                    List<MovieDataModel> similarMovies = new ArrayList<>();
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
                        List<MovieReleaseDateDataModel> movieReleaseDateDataModels = movieReleaseDatesDataModel.getMovieReleaseDates();
                        if (movieReleaseDateDataModels != null && movieReleaseDateDataModels.size() > 0) {
                            for (MovieReleaseDateDataModel movieReleaseDateDataModel : movieReleaseDateDataModels) {
                                if (movieReleaseDateDataModel != null) {
                                    String iso31661 = movieReleaseDateDataModel.getIso31661();
                                    if (iso31661.equals(ISO_31661)) {
                                        List<ReleaseDateDataModel> releaseDateDataModels = movieReleaseDateDataModel.getReleaseDates();
                                        if (releaseDateDataModels != null && releaseDateDataModels.size() > 0) {
                                            for (ReleaseDateDataModel releaseDateDataModel : releaseDateDataModels) {
                                                if (!TextUtils.isEmpty(releaseDateDataModel.getCertification())) {
                                                    rating = releaseDateDataModel.getCertification();
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    return new MovieDetailsDomainModel(movieDataModel, cast, crew, similarMovies, rating);
                });
    }
    // endregion

}

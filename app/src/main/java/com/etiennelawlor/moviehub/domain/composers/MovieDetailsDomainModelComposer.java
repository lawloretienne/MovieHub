package com.etiennelawlor.moviehub.domain.composers;

import android.text.TextUtils;

import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDateDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDatesDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.ReleaseDateDataModel;
import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieDetailsDomainModelComposer {

    // region Constants
    private static final String ISO_31661 = "US";
    // endregion

    public MovieDetailsDomainModel compose(MovieDataModel movieDataModel, MovieCreditsDataModel movieCreditsDataModel, MoviesDataModel moviesDataModel, MovieReleaseDatesDataModel movieReleaseDatesDataModel){
        MovieDetailsDomainModel movieDetailsDomainModel = new MovieDetailsDomainModel();

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

        movieDetailsDomainModel.setMovie(movieDataModel);
        movieDetailsDomainModel.setCast(cast);
        movieDetailsDomainModel.setCrew(crew);
        movieDetailsDomainModel.setSimilarMovies(similarMovies);
        movieDetailsDomainModel.setRating(rating);
        return movieDetailsDomainModel;
    }
}
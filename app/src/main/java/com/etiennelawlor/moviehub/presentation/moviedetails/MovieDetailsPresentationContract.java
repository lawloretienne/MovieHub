package com.etiennelawlor.moviehub.presentation.moviedetails;

import com.etiennelawlor.moviehub.domain.models.MovieCreditDomainModel;
import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.etiennelawlor.moviehub.presentation.models.MovieCreditPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;

import java.util.List;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface MovieDetailsPresentationContract {

    interface View {
//        void setMovieDetailsDomainModel(MovieDetailsDomainModel movieDetailsDomainModel);
        void showToolbarTitle();
        void hideToolbarTitle();
        void showErrorView();
        void showOverview(String overview);
        void showDuration(String duration);
        void showGenres(String genres);
        void showStatus(String status);
        void showReleaseDate(String releaseDate);
        void showBudget(String budget);
        void showRevenue(String revenue);
        void showRating(String rating);
        void hideRatingView();

        // Call setter methods when they aren't being shown right away
        void setCast(List<MovieCreditDomainModel> cast);
        void setCrew(List<MovieCreditDomainModel> crew);
        void setSimilarMovies(List<MovieDomainModel> similarMovies);

        void showMovieDetailsBodyView();


        // Navigation methods
        void openPersonDetails(PersonPresentationModel person);
        void openMovieDetails(MovieDomainModel movie);
    }

    interface Presenter extends BasePresenter {
        void onLoadMovieDetails(int movieId);
        void onPersonClick(PersonPresentationModel person);
        void onMovieClick(MovieDomainModel movie);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

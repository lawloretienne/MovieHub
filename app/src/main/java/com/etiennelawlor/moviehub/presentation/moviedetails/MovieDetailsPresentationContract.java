package com.etiennelawlor.moviehub.presentation.moviedetails;

import android.support.annotation.StringRes;

import com.etiennelawlor.moviehub.domain.models.MovieCreditDomainModel;
import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;

import java.util.List;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface MovieDetailsPresentationContract {

    interface View {
        void showToolbarTitle();
        void hideToolbarTitle();
        void showErrorView();
        void showOverview(String overview);
        void showOverview(@StringRes int resource);
        void showDuration(String duration);
        void showDuration(@StringRes int resource);
        void showGenres(String genres);
        void showGenres(@StringRes int resource);
        void showStatus(String status);
        void showStatus(@StringRes int resource);
        void showReleaseDate(String releaseDate);
        void showReleaseDate(@StringRes int resource);
        void showBudget(String budget);
        void showBudget(@StringRes int resource);
        void showRevenue(String revenue);
        void showRevenue(@StringRes int resource);
        void showRating(String rating);
        void hideRatingView();
        void showMovieDetailsBodyView();

        // Call setter methods when they aren't being shown right away
        void setCast(List<MovieCreditDomainModel> cast);
        void setCrew(List<MovieCreditDomainModel> crew);
        void setSimilarMovies(List<MovieDomainModel> similarMovies);

        // Navigation methods
        void openPersonDetails(PersonPresentationModel person);
        void openMovieDetails(MovieDomainModel movie);
    }

    interface Presenter extends BasePresenter {
        void onLoadMovieDetails(long movieId);
        void onPersonClick(PersonPresentationModel person);
        void onMovieClick(MovieDomainModel movie);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

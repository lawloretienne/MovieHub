package com.etiennelawlor.moviehub.presentation.moviedetails;

import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;
import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface MovieDetailsUiContract {

    interface View {
        void showMovieDetails(MovieDetailsDomainModel movieDetailsDomainModel);
        void showToolbarTitle();
        void hideToolbarTitle();
        void showErrorView();

        // Navigation methods
        void openPersonDetails(PersonDataModel person);
        void openMovieDetails(MovieDomainModel movie);
    }

    interface Presenter extends BasePresenter {
        void onLoadMovieDetails(int movieId);
        void onPersonClick(PersonDataModel person);
        void onMovieClick(MovieDomainModel movie);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

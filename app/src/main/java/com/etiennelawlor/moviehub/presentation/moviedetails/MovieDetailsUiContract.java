package com.etiennelawlor.moviehub.presentation.moviedetails;

import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;
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
        void openPersonDetails(PersonResponse person);
        void openMovieDetails(MovieDataModel movie);
    }

    interface Presenter extends BasePresenter {
        void onLoadMovieDetails(int movieId);
        void onPersonClick(PersonResponse person);
        void onMovieClick(MovieDataModel movie);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

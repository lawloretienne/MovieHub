package com.etiennelawlor.moviehub.presentation.persondetails;

import com.etiennelawlor.moviehub.data.network.response.MovieResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.domain.models.PersonDetailsDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface PersonDetailsUiContract {

    interface View {
        void showPersonDetails(PersonDetailsDomainModel personDetailsDomainModel);
        void showToolbarTitle();
        void hideToolbarTitle();
        void showErrorView();

        // Navigation methods
        void openMovieDetails(MovieResponse movie);
        void openTelevisionShowDetails(TelevisionShowResponse televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadPersonDetails(int personId);
        void onMovieClick(MovieResponse movie);
        void onTelevisionShowClick(TelevisionShowResponse televisionShow);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

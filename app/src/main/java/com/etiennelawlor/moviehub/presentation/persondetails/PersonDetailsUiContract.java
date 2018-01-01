package com.etiennelawlor.moviehub.presentation.persondetails;

import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
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
        void openMovieDetails(MovieDataModel movie);
        void openTelevisionShowDetails(TelevisionShowDataModel televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadPersonDetails(int personId);
        void onMovieClick(MovieDataModel movie);
        void onTelevisionShowClick(TelevisionShowDataModel televisionShow);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

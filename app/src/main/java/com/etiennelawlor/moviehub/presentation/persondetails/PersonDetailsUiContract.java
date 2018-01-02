package com.etiennelawlor.moviehub.presentation.persondetails;

import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonDetailsPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface PersonDetailsUiContract {

    interface View {
        void showPersonDetails(PersonDetailsPresentationModel personDetailsPresentationModel);
        void showToolbarTitle();
        void hideToolbarTitle();
        void showErrorView();

        // Navigation methods
        void openMovieDetails(MoviePresentationModel movie);
        void openTelevisionShowDetails(TelevisionShowPresentationModel televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadPersonDetails(int personId);
        void onMovieClick(MoviePresentationModel movie);
        void onTelevisionShowClick(TelevisionShowPresentationModel televisionShow);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

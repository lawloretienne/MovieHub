package com.etiennelawlor.moviehub.presentation.persondetails;

import com.etiennelawlor.moviehub.data.model.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface PersonDetailsUiContract {

    interface View {
        void showPersonDetails(PersonDetailsWrapper personDetailsWrapper);
        void showToolbarTitle();
        void hideToolbarTitle();
        void showErrorView();

        // Navigation methods
        void openMovieDetails(Movie movie);
        void openTelevisionShowDetails(TelevisionShow televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadPersonDetails(int personId);
        void onMovieClick(Movie movie);
        void onTelevisionShowClick(TelevisionShow televisionShow);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

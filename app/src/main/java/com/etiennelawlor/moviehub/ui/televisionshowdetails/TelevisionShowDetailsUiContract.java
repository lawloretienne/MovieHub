package com.etiennelawlor.moviehub.ui.televisionshowdetails;

import com.etiennelawlor.moviehub.data.model.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.data.remote.response.Person;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.ui.base.BasePresenter;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface TelevisionShowDetailsUiContract {

    interface View {
        void showTelevisionShowDetails(TelevisionShowDetailsWrapper televisionShowDetailsWrapper);
        void showToolbarTitle();
        void hideToolbarTitle();
        void showErrorView();

        // Navigation methods
        void openPersonDetails(Person person);
        void openTelevisionShowDetails(TelevisionShow televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadTelevisionShowDetails(int televisionShowId);
        void onPersonClick(Person person);
        void onTelevisionShowClick(TelevisionShow televisionShow);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

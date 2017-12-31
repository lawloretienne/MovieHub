package com.etiennelawlor.moviehub.presentation.televisionshowdetails;

import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface TelevisionShowDetailsUiContract {

    interface View {
        void showTelevisionShowDetails(TelevisionShowDetailsDomainModel televisionShowDetailsDomainModel);
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

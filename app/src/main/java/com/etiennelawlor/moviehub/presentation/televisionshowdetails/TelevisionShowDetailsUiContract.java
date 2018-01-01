package com.etiennelawlor.moviehub.presentation.televisionshowdetails;

import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
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
        void openPersonDetails(PersonResponse person);
        void openTelevisionShowDetails(TelevisionShowDataModel televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadTelevisionShowDetails(int televisionShowId);
        void onPersonClick(PersonResponse person);
        void onTelevisionShowClick(TelevisionShowDataModel televisionShow);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

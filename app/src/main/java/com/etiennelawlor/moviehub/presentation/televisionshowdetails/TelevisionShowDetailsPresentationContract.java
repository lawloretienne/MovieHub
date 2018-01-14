package com.etiennelawlor.moviehub.presentation.televisionshowdetails;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface TelevisionShowDetailsPresentationContract {

    interface View {
        void setTelevisionShowDetailsDomainModel(TelevisionShowDetailsDomainModel televisionShowDetailsDomainModel);
        void showToolbarTitle();
        void hideToolbarTitle();
        void showErrorView();

        // Navigation methods
        void openPersonDetails(PersonPresentationModel person);
        void openTelevisionShowDetails(TelevisionShowPresentationModel televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadTelevisionShowDetails(int televisionShowId);
        void onPersonClick(PersonPresentationModel person);
        void onTelevisionShowClick(TelevisionShowPresentationModel televisionShow);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

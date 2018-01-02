package com.etiennelawlor.moviehub.presentation.televisionshowdetails;

import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
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
        void openPersonDetails(PersonDomainModel person);
        void openTelevisionShowDetails(TelevisionShowDomainModel televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadTelevisionShowDetails(int televisionShowId);
        void onPersonClick(PersonDomainModel person);
        void onTelevisionShowClick(TelevisionShowDomainModel televisionShow);
        void onScrollChange(boolean isScrolledPastThreshold);
    }
}

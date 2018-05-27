package com.etiennelawlor.moviehub.presentation.televisionshows;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowsDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;

import java.util.List;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface TelevisionShowsPresentationContract {

    interface View {
        void showEmptyView();
        void hideEmptyView();
        void showErrorView();
        void hideErrorView();
        void showLoadingView();
        void hideLoadingView();
        void addHeaderView();
        void addFooterView();
        void removeFooterView();
        void showErrorFooterView();
        void showLoadingFooterView();
        void showTelevisionShows(List<TelevisionShowDomainModel> televisionShows);
        void loadMoreTelevisionShows();
        void setTelevisionShowsDomainModel(TelevisionShowsDomainModel televisionShowsDomainModel);

        // Navigation methods
        void openTelevisionShowDetails(TelevisionShowPresentationModel televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularTelevisionShows(int currentPage);
        void onTelevisionShowClick(TelevisionShowPresentationModel televisionShow);
        void onScrollToEndOfList();
    }
}

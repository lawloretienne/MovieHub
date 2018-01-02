package com.etiennelawlor.moviehub.presentation.televisionshows;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowsDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;

import java.util.List;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface TelevisionShowsUiContract {

    interface View {
        void showEmptyView();
        void hideEmptyView();
        void showErrorView();
        void hideErrorView();
        void setErrorText(String errorText);
        void showLoadingView();
        void hideLoadingView();
        void addHeader();
        void addFooter();
        void removeFooter();
        void showErrorFooter();
        void showLoadingFooter();
        void addTelevisionShowsToAdapter(List<TelevisionShowDomainModel> televisionShows);
        void loadMoreItems();
        void setTelevisionShowsDomainModel(TelevisionShowsDomainModel televisionShowsDomainModel);

        // Navigation methods
        void openTelevisionShowDetails(TelevisionShowDomainModel televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularTelevisionShows(int currentPage);
        void onTelevisionShowClick(TelevisionShowDomainModel televisionShow);
        void onScrollToEndOfList();
    }
}

package com.etiennelawlor.moviehub.presentation.televisionshows;

import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowsPresentationModel;

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
        void addTelevisionShowsToAdapter(List<TelevisionShowPresentationModel> televisionShows);
        void loadMoreItems();
        void setTelevisionShowsPresentationModel(TelevisionShowsPresentationModel televisionShowsPresentationModel);

        // Navigation methods
        void openTelevisionShowDetails(TelevisionShowPresentationModel televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularTelevisionShows(int currentPage);
        void onTelevisionShowClick(TelevisionShowPresentationModel televisionShow);
        void onScrollToEndOfList();
    }
}

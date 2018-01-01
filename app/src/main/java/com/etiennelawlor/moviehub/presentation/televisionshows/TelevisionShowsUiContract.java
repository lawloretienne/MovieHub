package com.etiennelawlor.moviehub.presentation.televisionshows;

import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;
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
        void addTelevisionShowsToAdapter(List<TelevisionShowDataModel> televisionShows);
        void loadMoreItems();
        void setTelevisionShowsDataModel(TelevisionShowsDataModel televisionShowsDataModel);

        // Navigation methods
        void openTelevisionShowDetails(TelevisionShowDataModel televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularTelevisionShows(int currentPage);
        void onTelevisionShowClick(TelevisionShowDataModel televisionShow);
        void onScrollToEndOfList();
    }
}

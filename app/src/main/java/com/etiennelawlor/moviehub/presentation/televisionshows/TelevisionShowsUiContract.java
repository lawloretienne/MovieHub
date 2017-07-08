package com.etiennelawlor.moviehub.presentation.televisionshows;

import com.etiennelawlor.moviehub.data.source.tv.models.TelevisionShowsPage;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
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
        void addTelevisionShowsToAdapter(List<TelevisionShow> televisionShows);
        void loadMoreItems();
        void setTelevisionShowsPage(TelevisionShowsPage televisionShowsPage);

        // Navigation methods
        void openTelevisionShowDetails(TelevisionShow televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularTelevisionShows(int currentPage);
        void onTelevisionShowClick(TelevisionShow televisionShow);
        void onScrollToEndOfList();
    }
}

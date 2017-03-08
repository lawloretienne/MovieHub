package com.etiennelawlor.moviehub.ui.televisionshows;

import com.etiennelawlor.moviehub.data.model.PagingInfo;
import com.etiennelawlor.moviehub.data.model.TelevisionShowsWrapper;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.ui.base.BasePresenter;

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
        void setPagingInfo(PagingInfo pagingInfo);

        // Navigation methods
        void openTelevisionShowDetails(TelevisionShow televisionShow);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularTelevisionShows(int currentPage);
        void onTelevisionShowClick(TelevisionShow televisionShow);
        void onScrollToEndOfList();
    }
}

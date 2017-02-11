package com.etiennelawlor.moviehub.ui.movies;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface MoviesContract {

    interface View {
        enum FooterType {
            LOAD_MORE,
            ERROR
        }

        void showEmptyView();
        void hideEmptyView();
        void showErrorView();
        void hideErrorView();
        void showLoadingView();
        void hideLoadingView();
        void updateFooter(FooterType footerType);
    }

    interface Presenter {
        enum PageType {
            FIRST_PAGE,
            NEXT_PAGE
        }

        void loadMovies(PageType pageType);
        void reloadMovies(PageType pageType);

        void onAttachView(View view);
        void onDetachView();
        void unsubscribeCompositeSubscription();
    }
}

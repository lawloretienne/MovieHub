package com.etiennelawlor.moviehub.ui.movies;

import com.etiennelawlor.moviehub.data.remote.response.Configuration;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.ui.base.BasePresenter;

import java.util.List;

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
        void addFooter();
        void removeFooter();
        void updateFooter(FooterType footerType);
        boolean isAdapterEmpty();
        void setErrorText(String errorText);
        void addMoviesToAdapter(List<Movie> movies);
        void viewMovieDetails(Movie movie);
        void saveConfiguration(Configuration configuration);
    }

    interface Presenter extends BasePresenter {
//        void loadFirstPage();
//        void loadNextPage();
//        void reloadFirstPage();
//        void reloadNextPage();

        void loadMovies(int currentPage);
//        void reloadMovies(int currentPage);
        void getConfiguration();
        void viewMovieDetails(Movie movie);
    }
}

package com.etiennelawlor.moviehub.ui.movies;

import com.etiennelawlor.moviehub.data.remote.response.Configuration;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.viewmodel.MoviesViewModel;
import com.etiennelawlor.moviehub.ui.base.BasePresenter;

import java.util.List;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface MoviesUIContract {

    interface View {
        enum FooterType {
            LOAD_MORE,
            ERROR
        }

        void showEmptyView();
        void hideEmptyView();
        void showErrorView();
        void hideErrorView();
        void setErrorText(String errorText);
        void showLoadingView();
        void hideLoadingView();
        void addFooter();
        void removeFooter();
        void updateFooter(FooterType footerType);
        void addMoviesToAdapter(List<Movie> movies);
        void loadMoreItems();
        void setViewModel(MoviesViewModel moviesViewModel);

        // Navigation methods
        void openMovieDetails(Movie movie);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularMovies(int currentPage);
        void onMovieClick(Movie movie);
        void onScrollToEndOfList();
    }
}

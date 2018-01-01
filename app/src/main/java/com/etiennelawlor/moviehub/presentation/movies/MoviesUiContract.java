package com.etiennelawlor.moviehub.presentation.movies;

import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;

import java.util.List;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface MoviesUiContract {

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
        void addMoviesToAdapter(List<MovieDataModel> movies);
        void loadMoreItems();
        void setMoviesDataModel(MoviesDataModel moviesDataModel);

        // Navigation methods
        void openMovieDetails(MovieDataModel movie);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularMovies(int currentPage);
        void onMovieClick(MovieDataModel movie);
        void onScrollToEndOfList();
    }
}

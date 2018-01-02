package com.etiennelawlor.moviehub.presentation.movies;

import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.MoviesPresentationModel;

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
        void addMoviesToAdapter(List<MoviePresentationModel> movies);
        void loadMoreItems();
        void setMoviesPresentationModel(MoviesPresentationModel moviesPresentationModel);

        // Navigation methods
        void openMovieDetails(MoviePresentationModel movie);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularMovies(int currentPage);
        void onMovieClick(MoviePresentationModel movie);
        void onScrollToEndOfList();
    }
}

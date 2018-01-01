package com.etiennelawlor.moviehub.presentation.movies;

import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.domain.models.MoviesDomainModel;
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
        void addMoviesToAdapter(List<MovieDomainModel> movies);
        void loadMoreItems();
        void setMoviesDomainModel(MoviesDomainModel moviesDataModel);

        // Navigation methods
        void openMovieDetails(MovieDomainModel movie);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularMovies(int currentPage);
        void onMovieClick(MovieDomainModel movie);
        void onScrollToEndOfList();
    }
}

package com.etiennelawlor.moviehub.presentation.movies;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;
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
        void addMoviesToAdapter(List<Movie> movies);
        void loadMoreItems();
        void setMoviesPage(MoviesPage moviesPage);

        // Navigation methods
        void openMovieDetails(Movie movie);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularMovies(int currentPage);
        void onMovieClick(Movie movie);
        void onScrollToEndOfList();
    }
}

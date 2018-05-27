package com.etiennelawlor.moviehub.presentation.movies;

import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.domain.models.MoviesDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;

import java.util.List;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface MoviesPresentationContract {

    interface View {
        void showEmptyView();
        void hideEmptyView();
        void showErrorView();
        void hideErrorView();
        void showLoadingView();
        void hideLoadingView();
        void addHeaderView();
        void addFooterView();
        void removeFooterView();
        void showErrorFooterView();
        void showLoadingFooterView();
        void showMovies(List<MovieDomainModel> movies);
        void loadMoreMovies();
        void setMoviesDomainModel(MoviesDomainModel moviesDomainModel);

        // Navigation methods
        void openMovieDetails(MoviePresentationModel movie);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularMovies(int currentPage);
        void onMovieClick(MoviePresentationModel movie);
        void onScrollToEndOfList();
    }
}

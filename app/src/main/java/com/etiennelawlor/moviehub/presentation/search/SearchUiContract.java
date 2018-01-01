package com.etiennelawlor.moviehub.presentation.search;

import com.etiennelawlor.moviehub.data.network.response.MovieResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface SearchUiContract {

    interface View {
        void showEmptyView();
        void hideEmptyView();
        void setEmptyText(String emptyText);
        void showLoadingView();
        void hideLoadingView();
        void showErrorView();
        void addMoviesToAdapter(List<MovieResponse> movies);
        void clearMoviesAdapter();
        void hideMoviesView();
        void showMoviesView();
        void addTelevisionShowsToAdapter(List<TelevisionShowResponse> televisionShows);
        void clearTelevisionShowsAdapter();
        void hideTelevisionShowsView();
        void showTelevisionShowsView();
        void addPersonsToAdapter(List<PersonResponse> persons);
        void clearPersonsAdapter();
        void hidePersonsView();
        void showPersonsView();

        // Navigation methods
        void openMovieDetails(MovieResponse movie);
        void openTelevisionShowDetails(TelevisionShowResponse televisionShow);
        void openPersonDetails(PersonResponse person);
    }

    interface Presenter extends BasePresenter {
        void onLoadSearch(Observable<CharSequence> searchQueryChangeObservable);
        void onMovieClick(MovieResponse movie);
        void onTelevisionShowClick(TelevisionShowResponse televisionShow);
        void onPersonClick(PersonResponse person);
    }
}

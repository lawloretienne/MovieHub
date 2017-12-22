package com.etiennelawlor.moviehub.presentation.search;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.jakewharton.rxbinding2.InitialValueObservable;

import java.util.List;

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
        void addMoviesToAdapter(List<Movie> movies);
        void clearMoviesAdapter();
        void hideMoviesView();
        void showMoviesView();
        void addTelevisionShowsToAdapter(List<TelevisionShow> televisionShows);
        void clearTelevisionShowsAdapter();
        void hideTelevisionShowsView();
        void showTelevisionShowsView();
        void addPersonsToAdapter(List<Person> persons);
        void clearPersonsAdapter();
        void hidePersonsView();
        void showPersonsView();

        // Navigation methods
        void openMovieDetails(Movie movie);
        void openTelevisionShowDetails(TelevisionShow televisionShow);
        void openPersonDetails(Person person);
    }

    interface Presenter extends BasePresenter {
        void onLoadSearch(InitialValueObservable<CharSequence> searchQueryChangeObservable);
        void onMovieClick(Movie movie);
        void onTelevisionShowClick(TelevisionShow televisionShow);
        void onPersonClick(Person person);
    }
}

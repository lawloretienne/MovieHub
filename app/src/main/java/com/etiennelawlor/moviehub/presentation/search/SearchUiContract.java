package com.etiennelawlor.moviehub.presentation.search;

import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
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
        void addMoviesToAdapter(List<MovieDataModel> movies);
        void clearMoviesAdapter();
        void hideMoviesView();
        void showMoviesView();
        void addTelevisionShowsToAdapter(List<TelevisionShowDataModel> televisionShows);
        void clearTelevisionShowsAdapter();
        void hideTelevisionShowsView();
        void showTelevisionShowsView();
        void addPersonsToAdapter(List<PersonResponse> persons);
        void clearPersonsAdapter();
        void hidePersonsView();
        void showPersonsView();

        // Navigation methods
        void openMovieDetails(MovieDataModel movie);
        void openTelevisionShowDetails(TelevisionShowDataModel televisionShow);
        void openPersonDetails(PersonResponse person);
    }

    interface Presenter extends BasePresenter {
        void onLoadSearch(Observable<CharSequence> searchQueryChangeObservable);
        void onMovieClick(MovieDataModel movie);
        void onTelevisionShowClick(TelevisionShowDataModel televisionShow);
        void onPersonClick(PersonResponse person);
    }
}

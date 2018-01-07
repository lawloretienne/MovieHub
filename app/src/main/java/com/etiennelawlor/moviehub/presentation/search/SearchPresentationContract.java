package com.etiennelawlor.moviehub.presentation.search;

import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface SearchPresentationContract {

    interface View {
        void showEmptyView();
        void hideEmptyView();
        void setEmptyText(String emptyText);
        void showLoadingView();
        void hideLoadingView();
        void showErrorView();
        void addMoviesToAdapter(List<MoviePresentationModel> movies);
        void clearMoviesAdapter();
        void hideMoviesView();
        void showMoviesView();
        void addTelevisionShowsToAdapter(List<TelevisionShowPresentationModel> televisionShows);
        void clearTelevisionShowsAdapter();
        void hideTelevisionShowsView();
        void showTelevisionShowsView();
        void addPersonsToAdapter(List<PersonPresentationModel> persons);
        void clearPersonsAdapter();
        void hidePersonsView();
        void showPersonsView();

        // Navigation methods
        void openMovieDetails(MoviePresentationModel movie);
        void openTelevisionShowDetails(TelevisionShowPresentationModel televisionShow);
        void openPersonDetails(PersonPresentationModel person);
    }

    interface Presenter extends BasePresenter {
        void onLoadSearch(Observable<CharSequence> searchQueryChangeObservable);
        void onMovieClick(MoviePresentationModel movie);
        void onTelevisionShowClick(TelevisionShowPresentationModel televisionShow);
        void onPersonClick(PersonPresentationModel person);
    }
}

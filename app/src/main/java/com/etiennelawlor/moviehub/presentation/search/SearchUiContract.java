package com.etiennelawlor.moviehub.presentation.search;

import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
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
        void addMoviesToAdapter(List<MovieDomainModel> movies);
        void clearMoviesAdapter();
        void hideMoviesView();
        void showMoviesView();
        void addTelevisionShowsToAdapter(List<TelevisionShowDomainModel> televisionShows);
        void clearTelevisionShowsAdapter();
        void hideTelevisionShowsView();
        void showTelevisionShowsView();
        void addPersonsToAdapter(List<PersonDomainModel> persons);
        void clearPersonsAdapter();
        void hidePersonsView();
        void showPersonsView();

        // Navigation methods
        void openMovieDetails(MovieDomainModel movie);
        void openTelevisionShowDetails(TelevisionShowDomainModel televisionShow);
        void openPersonDetails(PersonDomainModel person);
    }

    interface Presenter extends BasePresenter {
        void onLoadSearch(Observable<CharSequence> searchQueryChangeObservable);
        void onMovieClick(MovieDomainModel movie);
        void onTelevisionShowClick(TelevisionShowDomainModel televisionShow);
        void onPersonClick(PersonDomainModel person);
    }
}

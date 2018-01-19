package com.etiennelawlor.moviehub.presentation.search;

import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
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
        void openTelevisionShowDetails(TelevisionShowPresentationModel televisionShow);
        void openPersonDetails(PersonPresentationModel person);
    }

    interface Presenter extends BasePresenter {
        void onLoadSearch(Observable<CharSequence> searchQueryChangeObservable);
        void onMovieClick(MovieDomainModel movie);
        void onTelevisionShowClick(TelevisionShowPresentationModel televisionShow);
        void onPersonClick(PersonPresentationModel person);
    }
}

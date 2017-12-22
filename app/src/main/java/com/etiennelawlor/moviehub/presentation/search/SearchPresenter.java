package com.etiennelawlor.moviehub.presentation.search;

import android.util.Log;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;
import com.etiennelawlor.moviehub.domain.SearchDomainContract;
import com.etiennelawlor.moviehub.util.NetworkUtility;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class SearchPresenter implements SearchUiContract.Presenter {

    // region Member Variables
    private final SearchUiContract.View searchView;
    private final SearchDomainContract.UseCase searchUseCase;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors
    public SearchPresenter(SearchUiContract.View searchView, SearchDomainContract.UseCase searchUseCase) {
        this.searchView = searchView;
        this.searchUseCase = searchUseCase;
    }
    // endregion

    // region SearchUiContract.Presenter Methods

    @Override
    public void onDestroyView() {
        if(compositeSubscription != null && compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void onLoadSearch(Observable<CharSequence> searchQueryChangeObservable) {
        Subscription subscription = searchQueryChangeObservable
                .doOnNext(charSequence -> searchView.hideLoadingView())
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(charSequence -> {
                    if(isEmpty(charSequence)){
                        searchView.hideLoadingView();

                        searchView.clearMoviesAdapter();
                        searchView.hideMoviesView();

                        searchView.clearTelevisionShowsAdapter();
                        searchView.hideTelevisionShowsView();

                        searchView.clearPersonsAdapter();
                        searchView.hidePersonsView();

                        searchView.hideEmptyView();
                    } else {
                        searchView.showLoadingView();
                    }

                    return !isEmpty(charSequence);
                })
                .map(charSequence -> charSequence.toString())
//                .switchMap(q -> {
//                    return searchRepository.getSearch(q);
//                })
                .switchMap(q -> Observable.just(q))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        searchUseCase.getSearchResponse(s, new Subscriber<SearchWrapper>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable throwable) {
                                throwable.printStackTrace();

                                searchView.hideLoadingView();

                                if (NetworkUtility.isKnownException(throwable)) {
                                    searchView.showErrorView();
                                }
                            }

                            @Override
                            public void onNext(SearchWrapper searchWrapper) {
                                searchView.hideLoadingView();
                                if (searchWrapper != null) {
                                    searchView.clearMoviesAdapter();
                                    searchView.clearTelevisionShowsAdapter();
                                    searchView.clearPersonsAdapter();

                                    if(searchWrapper.hasMovies()){
                                        searchView.addMoviesToAdapter(searchWrapper.getMovies());
                                        searchView.showMoviesView();
                                    } else {
                                        searchView.hideMoviesView();
                                    }

                                    if(searchWrapper.hasTelevisionShows()){
                                        searchView.addTelevisionShowsToAdapter(searchWrapper.getTelevisionShows());
                                        searchView.showTelevisionShowsView();
                                    } else {
                                        searchView.hideTelevisionShowsView();
                                    }

                                    if(searchWrapper.hasPersons()){
                                        searchView.addPersonsToAdapter(searchWrapper.getPersons());
                                        searchView.showPersonsView();
                                    } else {
                                        searchView.hidePersonsView();
                                    }

                                    if(searchWrapper.hasResults()){
                                        searchView.hideEmptyView();
                                    } else {
                                        searchView.setEmptyText(String.format("No results found for \"%s\"", searchWrapper.getQuery()));
                                        searchView.showEmptyView();
                                    }
                                }
                            }
                        });
                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void onMovieClick(Movie movie) {
        searchView.openMovieDetails(movie);
    }

    @Override
    public void onTelevisionShowClick(TelevisionShow televisionShow) {
        searchView.openTelevisionShowDetails(televisionShow);
    }

    @Override
    public void onPersonClick(Person person) {
        searchView.openPersonDetails(person);
    }

    // endregion

    // region Helper Methods
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
    // endregion
}

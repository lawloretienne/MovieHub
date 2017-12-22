package com.etiennelawlor.moviehub.presentation.search;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;
import com.etiennelawlor.moviehub.domain.SearchDomainContract;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class SearchPresenter implements SearchUiContract.Presenter {

    // region Member Variables
    private final SearchUiContract.View searchView;
    private final SearchDomainContract.UseCase searchUseCase;
    private final SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors
    public SearchPresenter(SearchUiContract.View searchView, SearchDomainContract.UseCase searchUseCase, SchedulerProvider schedulerProvider) {
        this.searchView = searchView;
        this.searchUseCase = searchUseCase;
        this.schedulerProvider = schedulerProvider;
    }
    // endregion

    // region SearchUiContract.Presenter Methods

    @Override
    public void onDestroyView() {
        if(compositeDisposable != null && compositeDisposable.isDisposed())
            compositeDisposable.clear();
    }

    @Override
    public void onLoadSearch(Observable<CharSequence> searchQueryChangeObservable) {
        Disposable disposable = searchQueryChangeObservable
                .doOnNext(charSequence -> searchView.hideLoadingView())
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(schedulerProvider.ui())
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
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        searchUseCase.getSearchResponse(s, new DisposableSingleObserver<SearchWrapper>() {
                            @Override
                            public void onSuccess(SearchWrapper searchWrapper) {
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

                            @Override
                            public void onError(Throwable throwable) {
                                throwable.printStackTrace();

                                searchView.hideLoadingView();

                                if (NetworkUtility.isKnownException(throwable)) {
                                    searchView.showErrorView();
                                }
                            }
                        });
                    }
                });
        compositeDisposable.add(disposable);
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

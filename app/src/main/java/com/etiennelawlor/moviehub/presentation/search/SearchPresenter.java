package com.etiennelawlor.moviehub.presentation.search;

import com.etiennelawlor.moviehub.data.network.response.MovieResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.domain.SearchDomainContract;
import com.etiennelawlor.moviehub.domain.models.SearchDomainModel;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;

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
        if(compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void onLoadSearch(Observable<CharSequence> searchQueryChangeObservable) {
        Disposable outerDisposable = searchQueryChangeObservable
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
                        Disposable innerDisposable = searchUseCase.getSearchResponse(s)
                            //  .compose(schedulerTransformer)
                                .compose(new ProductionSchedulerTransformer<>())
                                .subscribeWith(new DisposableSingleObserver<SearchDomainModel>() {
                                    @Override
                                    public void onSuccess(SearchDomainModel searchDomainModel) {
                                        searchView.hideLoadingView();
                                        if (searchDomainModel != null) {
                                            searchView.clearMoviesAdapter();
                                            searchView.clearTelevisionShowsAdapter();
                                            searchView.clearPersonsAdapter();

                                            if(searchDomainModel.hasMovies()){
                                                searchView.addMoviesToAdapter(searchDomainModel.getMovies());
                                                searchView.showMoviesView();
                                            } else {
                                                searchView.hideMoviesView();
                                            }

                                            if(searchDomainModel.hasTelevisionShows()){
                                                searchView.addTelevisionShowsToAdapter(searchDomainModel.getTelevisionShows());
                                                searchView.showTelevisionShowsView();
                                            } else {
                                                searchView.hideTelevisionShowsView();
                                            }

                                            if(searchDomainModel.hasPersons()){
                                                searchView.addPersonsToAdapter(searchDomainModel.getPersons());
                                                searchView.showPersonsView();
                                            } else {
                                                searchView.hidePersonsView();
                                            }

                                            if(searchDomainModel.hasResults()){
                                                searchView.hideEmptyView();
                                            } else {
                                                searchView.setEmptyText(String.format("No results found for \"%s\"", searchDomainModel.getQuery()));
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

                        compositeDisposable.add(innerDisposable);
                    }
                });
        compositeDisposable.add(outerDisposable);
    }

    @Override
    public void onMovieClick(MovieResponse movie) {
        searchView.openMovieDetails(movie);
    }

    @Override
    public void onTelevisionShowClick(TelevisionShowResponse televisionShow) {
        searchView.openTelevisionShowDetails(televisionShow);
    }

    @Override
    public void onPersonClick(PersonResponse person) {
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

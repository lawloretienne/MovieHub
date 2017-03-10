package com.etiennelawlor.moviehub.ui.search;

import android.text.TextUtils;

import com.etiennelawlor.moviehub.data.model.SearchWrapper;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.Person;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.source.search.SearchDataSourceContract;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class SearchPresenter implements SearchUiContract.Presenter {

    // region Member Variables
    private final SearchUiContract.View searchView;
    private final SearchDataSourceContract.Repository searchRepository;
    private final SchedulerTransformer<SearchWrapper> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors
    public SearchPresenter(SearchUiContract.View searchView, SearchDataSourceContract.Repository searchRepository, SchedulerTransformer<SearchWrapper> schedulerTransformer) {
        this.searchView = searchView;
        this.searchRepository = searchRepository;
        this.schedulerTransformer = schedulerTransformer;
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
        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice

        Subscription subscription = searchQueryChangeObservable
                .doOnNext(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        searchView.hideLoadingView();
                    }
                })
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        if(TextUtils.isEmpty(charSequence)){
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

                        return !TextUtils.isEmpty(charSequence);
                    }
                })
                .map(new Func1<CharSequence, String>() {
                    @Override
                    public String call(CharSequence charSequence) {
                        return charSequence.toString();
                    }
                })
                .observeOn(Schedulers.io())
                .switchMap(new Func1<String, Observable<SearchWrapper>>() {
                    @Override
                    public Observable<SearchWrapper> call(String q) {
                        return searchRepository.getSearch(q).compose(schedulerTransformer);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) // UI Thread
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
                    }
                })
                .subscribe(new Subscriber<SearchWrapper>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("onCompleted()");
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
}

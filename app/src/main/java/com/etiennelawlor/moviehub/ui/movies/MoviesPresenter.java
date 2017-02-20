package com.etiennelawlor.moviehub.ui.movies;

import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.source.movies.MoviesDataContract;
import com.etiennelawlor.moviehub.data.viewmodel.MoviesViewModel;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesPresenter implements MoviesContract.Presenter {

    // region Constants
    private static final int PAGE_SIZE = 20;
    // endregion

    // region Member Variables
    private final MoviesDataContract.Repository moviesRepository;
    private final MoviesContract.View moviesView;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();;
    // endregion

    // region Constructors
    public MoviesPresenter(MoviesDataContract.Repository moviesRepository, MoviesContract.View moviesView) {
        this.moviesRepository = moviesRepository;
        this.moviesView = moviesView;
    }
    // endregion

    // region MoviesContract.Presenter Methods
    @Override
    public void clearSubscriptions() {
        compositeSubscription.clear();
    }

    @Override
    public void addSubscription(Observable observable, Subscriber subscriber) {
        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        compositeSubscription.add(subscription);

        // Create RxUtils for this call
    }

    @Override
    public void loadPopularMovies(final int currentPage) {
        if(currentPage == 1){
            moviesView.hideEmptyView();
            moviesView.hideErrorView();
            moviesView.showLoadingView();
        } else{
            moviesView.updateFooter(MoviesContract.View.FooterType.LOAD_MORE);
        }

        addSubscription(moviesRepository.getPopularMovies(currentPage), new Subscriber<MoviesViewModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(currentPage == 1){

                } else {

                }

                moviesView.hideLoadingView();

            }

            @Override
            public void onNext(MoviesViewModel moviesViewModel) {
                moviesView.hideLoadingView();

                if(moviesViewModel != null){
                    int currentPage = moviesViewModel.getCurrentPage();
                    List<Movie> movies = moviesViewModel.getMovies();
                    if(currentPage == 1){

                        if(movies != null){
                            if(movies.size()>0)
                                moviesView.addMoviesToAdapter(movies);

                            if(movies.size() >= PAGE_SIZE){  // instead do if !(viewmodel.islastPage) the add footer
                                moviesView.addFooter();
                            } else {
//                                    moviesView.setIsLastPage(true);
                            }
                        }
//                        moviesView.displayMovies(moviesViewModel.getMovies());

                        if(moviesView.isAdapterEmpty()){
                            moviesView.showEmptyView();
                        }
                    } else {
                        moviesView.removeFooter();
//                        moviesView.setIsLoading(false);

                        if(movies != null){
                            if(movies.size()>0)
                                moviesView.addMoviesToAdapter(movies);

                            if(movies.size() >= PAGE_SIZE){
                                moviesView.addFooter();
                            } else {
//                                moviesView.setIsLastPage(true);
                            }
                        }
                    }

                }

//                moviesView.setViewModel(moviesViewModel);
            }
        });
    }

//    @Override
//    public void getConfiguration() {
//        // difference between map and flatMap is map returns an Object and flatMap returns Observable<Object>
//
//        ///
////        moviesDataContract.getConfiguration().flatMap()
//        // configuration should be a property of the view model for adapters
//
//        // 1. moviesDataContract.getConfiguration()
//        // 2. moviesDataContract.getConfiguration().flatMap(v -> movieHubService.getPopularMovies(currentPage));
//
//        // then combineLatest(1, 2) - create view model wrapper object
//
////        Subscription subscription = moviesDataContract.getConfiguration()
////                .subscribeOn(Schedulers.newThread())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Action1<Configuration>() {
////                    @Override
////                    public void call(Configuration configuration) {
////                        if(configuration != null){
//////                            PreferencesHelper.setConfiguration(getContext(), configuration);
////                            moviesView.saveConfiguration(configuration);
////
//////                                Call getPopularMoviesCall = movieHubService.getPopularMovies(currentPage);
//////                                calls.add(getPopularMoviesCall);
//////                                getPopularMoviesCall.enqueue(getPopularMoviesFirstFetchCallback);
////
////                            loadMovies(0);
////                        }
////                    }
////                }, new Action1<Throwable>() {
////                    @Override
////                    public void call(Throwable throwable) {
////                        throwable.printStackTrace();
//////                        progressBar.setVisibility(View.GONE);
////                        moviesView.showLoadingView();
////                        if (NetworkUtility.isKnownException(throwable)) {
//////                            errorTextView.setText("Can't load data.\nCheck your network connection.");
//////                            errorLinearLayout.setVisibility(View.VISIBLE);
////                            moviesView.setErrorText("Can't load data.\nCheck your network connection.");
////                            moviesView.showErrorView();
////                        }
////                    }
////                });
////        compositeSubscription.add(subscription);
//    }


    @Override
    public void onMovieItemClick(Movie movie) {
        moviesView.openMovieDetails(movie);
    }

    @Override
    public void onScrolledToEndOfList() {
        moviesView.loadMoreItems();
    }

    // endregion

}

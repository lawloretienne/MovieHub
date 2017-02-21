package com.etiennelawlor.moviehub.ui.movies;

import com.etiennelawlor.moviehub.data.model.MoviesModel;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.source.movies.MoviesDataSourceContract;
import com.etiennelawlor.moviehub.util.NetworkUtility;

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

public class MoviesPresenter implements MoviesUIContract.Presenter {

    // region Member Variables
    private final MoviesDataSourceContract.Repository moviesRepository;
    private final MoviesUIContract.View moviesView;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors
    public MoviesPresenter(MoviesDataSourceContract.Repository moviesRepository, MoviesUIContract.View moviesView) {
        this.moviesRepository = moviesRepository;
        this.moviesView = moviesView;
    }
    // endregion

    // region MoviesUIContract.Presenter Methods
    @Override
    public void onDestroyView() {
        compositeSubscription.clear();
    }

    @Override
    public void onLoadPopularMovies(final int currentPage) {
        if(currentPage == 1){
            moviesView.hideEmptyView();
            moviesView.hideErrorView();
            moviesView.showLoadingView();
        } else{
            moviesView.showLoadingFooter();
        }

        addSubscription(moviesRepository.getPopularMovies(currentPage), new Subscriber<MoviesModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                if(currentPage == 1){
                    moviesView.hideLoadingView();

                    if (NetworkUtility.isKnownException(throwable)) {
                        moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                        moviesView.showErrorView();
                    }
                } else {
                    if(NetworkUtility.isKnownException(throwable)){
                        moviesView.showErrorFooter();
                    }
                }
            }

            @Override
            public void onNext(MoviesModel moviesModel) {
                moviesView.hideLoadingView();

                if(moviesModel != null){
                    int currentPage = moviesModel.getCurrentPage();
                    List<Movie> movies = moviesModel.getMovies();
                    if(currentPage == 1){
                        if(moviesModel.hasMovies()){
                            moviesView.addMoviesToAdapter(movies);

                            if(!moviesModel.isLastPage())
                                moviesView.addFooter();
                        } else {
                            moviesView.showEmptyView();
                        }
                    } else {
                        moviesView.removeFooter();
//                        moviesView.setIsLoading(false);

                        if(moviesModel.hasMovies()){
                            moviesView.addMoviesToAdapter(movies);

                            if(!moviesModel.isLastPage())
                                moviesView.addFooter();
                        }
                    }

                }

                moviesView.setModel(moviesModel);
            }
        });
    }

    @Override
    public void onMovieClick(Movie movie) {
        moviesView.openMovieDetails(movie);
    }

    @Override
    public void onScrollToEndOfList() {
        moviesView.loadMoreItems();
    }
    // endregion

    // region Helper Methods
    public void addSubscription(Observable observable, Subscriber subscriber) {
        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        compositeSubscription.add(subscription);

        // Create RxUtils for this call
    }
    // endregion

}

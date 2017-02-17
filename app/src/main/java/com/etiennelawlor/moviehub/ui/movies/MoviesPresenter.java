package com.etiennelawlor.moviehub.ui.movies;

import android.os.NetworkOnMainThreadException;

import com.etiennelawlor.moviehub.data.remote.response.Configuration;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.source.movies.MoviesDataSource;
import com.etiennelawlor.moviehub.data.source.movies.MoviesRepository;
import com.etiennelawlor.moviehub.data.viewmodel.MoviesViewModel;
import com.etiennelawlor.moviehub.ui.base.BasePresenter;
import com.etiennelawlor.moviehub.util.NetworkLogUtility;
import com.etiennelawlor.moviehub.util.NetworkUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesPresenter implements MoviesContract.Presenter {

    // region Constants
    private static final int PAGE_SIZE = 20;
    // endregion

    // region Member Variables
    private final MoviesDataSource moviesDataSource;
    private final MoviesContract.View moviesView;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();;
    // endregion

    // region Constructors
    public MoviesPresenter(MoviesDataSource moviesDataSource, MoviesContract.View moviesView) {
        this.moviesDataSource = moviesDataSource;
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
    }

    @Override
    public void loadMovies(int currentPage) {
        if(currentPage == 1){
            moviesView.hideEmptyView();
            moviesView.hideErrorView();
            moviesView.showLoadingView();
        } else{
            moviesView.updateFooter(MoviesContract.View.FooterType.LOAD_MORE);
        }

        addSubscription(moviesDataSource.getMovies(currentPage), new Subscriber<MoviesViewModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
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

                            if(movies.size() >= PAGE_SIZE){
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
            }
        });
    }

    @Override
    public void getConfiguration() {
//        Subscription subscription = moviesDataSource.getConfiguration()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Configuration>() {
//                    @Override
//                    public void call(Configuration configuration) {
//                        if(configuration != null){
////                            PreferencesHelper.setConfiguration(getContext(), configuration);
//                            moviesView.saveConfiguration(configuration);
//
////                                Call getPopularMoviesCall = movieHubService.getPopularMovies(currentPage);
////                                calls.add(getPopularMoviesCall);
////                                getPopularMoviesCall.enqueue(getPopularMoviesFirstFetchCallback);
//
//                            loadMovies(0);
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
////                        progressBar.setVisibility(View.GONE);
//                        moviesView.showLoadingView();
//                        if (NetworkUtility.isKnownException(throwable)) {
////                            errorTextView.setText("Can't load data.\nCheck your network connection.");
////                            errorLinearLayout.setVisibility(View.VISIBLE);
//                            moviesView.setErrorText("Can't load data.\nCheck your network connection.");
//                            moviesView.showErrorView();
//                        }
//                    }
//                });
//        compositeSubscription.add(subscription);
    }

    @Override
    public void viewMovieDetails(Movie movie) {
        moviesView.viewMovieDetails(movie);
    }
    // endregion

}

package com.etiennelawlor.moviehub.ui.movies;

import com.etiennelawlor.moviehub.data.model.MoviesWrapper;
import com.etiennelawlor.moviehub.data.model.PagingInfo;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.source.movies.MoviesDataSourceContract;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesPresenter implements MoviesUiContract.Presenter {

    // region Member Variables
    private final MoviesUiContract.View moviesView;
    private final MoviesDataSourceContract.Repository moviesRepository;
    private final SchedulerTransformer<MoviesWrapper> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors
    public MoviesPresenter(MoviesUiContract.View moviesView, MoviesDataSourceContract.Repository moviesRepository, SchedulerTransformer<MoviesWrapper> schedulerTransformer) {
        this.moviesView = moviesView;
        this.moviesRepository = moviesRepository;
        this.schedulerTransformer = schedulerTransformer;
    }
    // endregion

    // region MoviesUiContract.Presenter Methods
    @Override
    public void onDestroyView() {
        if(compositeSubscription != null && compositeSubscription.hasSubscriptions())
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

        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice

        Subscription subscription = moviesRepository.getPopularMovies(currentPage)
                .compose(schedulerTransformer)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
                    }
                })
                .subscribe(new Subscriber<MoviesWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

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
                    public void onNext(MoviesWrapper moviesWrapper) {
                        if(moviesWrapper != null){
                            PagingInfo pagingInfo = moviesWrapper.getPagingInfo();
                            int currentPage = pagingInfo.getCurrentPage();
                            boolean isLastPage = pagingInfo.isLastPage();

                            List<Movie> movies = moviesWrapper.getMovies();
                            boolean hasMovies = moviesWrapper.hasMovies();
                            if(currentPage == 1){
                                moviesView.hideLoadingView();

                                if(hasMovies){
                                    moviesView.addHeader();
                                    moviesView.addMoviesToAdapter(movies);

                                    if(!isLastPage)
                                        moviesView.addFooter();
                                } else {
                                    moviesView.showEmptyView();
                                }
                            } else {
                                moviesView.removeFooter();

                                if(hasMovies){
                                    moviesView.addMoviesToAdapter(movies);

                                    if(!isLastPage)
                                        moviesView.addFooter();
                                }
                            }

                            moviesView.setPagingInfo(pagingInfo);
                        }
                    }
                });
        compositeSubscription.add(subscription);
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

}

package com.etiennelawlor.moviehub.ui.movies;

import com.etiennelawlor.moviehub.data.model.MoviesPage;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.source.movie.MovieDataSourceContract;
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
    private final MovieDataSourceContract.Repository movieRepository;
    private final SchedulerTransformer<MoviesPage> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors
    public MoviesPresenter(MoviesUiContract.View moviesView, MovieDataSourceContract.Repository movieRepository, SchedulerTransformer<MoviesPage> schedulerTransformer) {
        this.moviesView = moviesView;
        this.movieRepository = movieRepository;
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

        Subscription subscription = movieRepository.getPopularMovies(currentPage)
                .compose(schedulerTransformer)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
                    }
                })
                .subscribe(new Subscriber<MoviesPage>() {
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
                    public void onNext(MoviesPage moviesPage) {
                        if(moviesPage != null){
                            List<Movie> movies = moviesPage.getMovies();
                            int currentPage = moviesPage.getPageNumber();
                            boolean isLastPage = moviesPage.isLastPage();
                            boolean hasMovies = moviesPage.hasMovies();
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

                            moviesView.setMoviesPage(moviesPage);
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

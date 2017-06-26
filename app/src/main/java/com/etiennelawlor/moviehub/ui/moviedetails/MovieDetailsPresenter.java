package com.etiennelawlor.moviehub.ui.moviedetails;

import com.etiennelawlor.moviehub.data.model.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.Person;
import com.etiennelawlor.moviehub.data.source.movie.MovieDataSourceContract;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MovieDetailsPresenter implements MovieDetailsUiContract.Presenter {

    // region Member Variables
    private final MovieDetailsUiContract.View movieDetailsView;
    private final MovieDataSourceContract.Repository movieRepository;
    private final SchedulerTransformer<MovieDetailsWrapper> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors
    public MovieDetailsPresenter(MovieDetailsUiContract.View movieDetailsView, MovieDataSourceContract.Repository movieRepository, SchedulerTransformer<MovieDetailsWrapper> schedulerTransformer) {
        this.movieDetailsView = movieDetailsView;
        this.movieRepository = movieRepository;
        this.schedulerTransformer = schedulerTransformer;
    }
    // endregion

    // region MovieDetailsUiContract.Presenter Methods
    @Override
    public void onDestroyView() {
        if(compositeSubscription != null && compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void onLoadMovieDetails(int movieId) {
        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice

        Subscription subscription = movieRepository.getMovieDetails(movieId)
                .compose(schedulerTransformer)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
                    }
                })
                .subscribe(new Subscriber<MovieDetailsWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        if(NetworkUtility.isKnownException(throwable)){
//                            moviesView.showErrorFooter();
//                            moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                            movieDetailsView.showErrorView();
                        }
                    }

                    @Override
                    public void onNext(MovieDetailsWrapper movieDetailsWrapper) {
                        if(movieDetailsWrapper != null){
                            movieDetailsView.showMovieDetails(movieDetailsWrapper);
                        }
                    }
                });
        compositeSubscription.add(subscription);

//        usecase.getMovieDetails(movieId, subscriber);
    }

    @Override
    public void onPersonClick(Person person) {
        movieDetailsView.openPersonDetails(person);
    }

    @Override
    public void onMovieClick(Movie movie) {
        movieDetailsView.openMovieDetails(movie);
    }

    @Override
    public void onScrollChange(boolean isScrolledPastThreshold) {
        if(isScrolledPastThreshold)
            movieDetailsView.showToolbarTitle();
        else
            movieDetailsView.hideToolbarTitle();
    }
    // endregion
}

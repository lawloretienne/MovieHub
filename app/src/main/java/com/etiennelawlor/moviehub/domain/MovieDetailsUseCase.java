package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.movie.MovieDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class MovieDetailsUseCase implements MovieDetailsDomainContract.UseCase {

    // region Member Variables
    private final MovieDataSourceContract.Repository movieRepository;
    private final SchedulerTransformer<MovieDetailsWrapper> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors
    public MovieDetailsUseCase(MovieDataSourceContract.Repository movieRepository, SchedulerTransformer<MovieDetailsWrapper> schedulerTransformer) {
        this.movieRepository = movieRepository;
        this.schedulerTransformer = schedulerTransformer;
    }
    // endregion

    // region MovieDetailsDomainContract.UseCase Methods
    @Override
    public void clearSubscriptions() {
        if(compositeSubscription != null && compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void getMovieDetails(int movieId, Subscriber subscriber) {
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
                .subscribe(subscriber);
        compositeSubscription.add(subscription);
    }
    // endregion

}

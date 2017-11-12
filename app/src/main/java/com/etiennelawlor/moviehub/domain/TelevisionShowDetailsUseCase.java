package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class TelevisionShowDetailsUseCase implements TelevisionShowDetailsDomainContract.UseCase {

    // region Member Variables
    private final TelevisionShowDataSourceContract.Repository televisionShowRepository;
    private final SchedulerTransformer<TelevisionShowDetailsWrapper> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors
    public TelevisionShowDetailsUseCase(TelevisionShowDataSourceContract.Repository televisionShowRepository, SchedulerTransformer<TelevisionShowDetailsWrapper> schedulerTransformer) {
        this.televisionShowRepository = televisionShowRepository;
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
    public void getTelevisionShowDetails(int televisionShowId, Subscriber subscriber) {
        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice

        Subscription subscription = televisionShowRepository.getTelevisionShowDetails(televisionShowId)
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

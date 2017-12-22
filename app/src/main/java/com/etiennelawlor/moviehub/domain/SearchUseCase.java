package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.search.SearchDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer2;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 12/22/17.
 */

public class SearchUseCase implements SearchDomainContract.UseCase {

    // region Member Variables
    private final SearchDataSourceContract.Repository searchRepository;
    private final SchedulerTransformer<SearchWrapper> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors

    public SearchUseCase(SearchDataSourceContract.Repository searchRepository, SchedulerTransformer<SearchWrapper> schedulerTransformer) {
        this.searchRepository = searchRepository;
        this.schedulerTransformer = schedulerTransformer;
    }

    // endregion

    @Override
    public void clearSubscriptions() {
        if(compositeSubscription != null && compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void getSearchResponse(String query, Subscriber subscriber) {
        Subscription subscription = searchRepository.getSearch(query)
                .compose(schedulerTransformer)
                .doOnSubscribe(() -> {
                    // The network request might be handled in a different thread so make sure Espresso knows
                    // that the app is busy until the response is handled.
                    EspressoIdlingResource.increment(); // App is busy until further notice
                })
                .doOnTerminate(() -> {
                    // https://github.com/VisheshVadhera/PlacementApp/blob/f36e8c259cbba37c1be90409016854f8c64bb8a5/app/src/main/java/com/vishesh/placement/core/useCases/BaseUseCase.java
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                })
                .subscribe(subscriber);
        compositeSubscription.add(subscription);
    }
}

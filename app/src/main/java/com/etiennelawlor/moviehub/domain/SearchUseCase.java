package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.search.SearchDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 12/22/17.
 */

public class SearchUseCase implements SearchDomainContract.UseCase {

    // region Member Variables
    private final SearchDataSourceContract.Repository searchRepository;
    private final SchedulerTransformer<SearchWrapper> schedulerTransformer;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors

    public SearchUseCase(SearchDataSourceContract.Repository searchRepository, SchedulerTransformer<SearchWrapper> schedulerTransformer) {
        this.searchRepository = searchRepository;
        this.schedulerTransformer = schedulerTransformer;
    }

    // endregion

    @Override
    public void clearSubscriptions() {
        if(compositeDisposable != null && compositeDisposable.isDisposed())
            compositeDisposable.clear();
    }

    @Override
    public void getSearchResponse(String query, DisposableSingleObserver disposableSingleObserver) {
        Disposable disposable = searchRepository.getSearch(query)
                .compose(schedulerTransformer)
                .doOnSubscribe(disposable1 -> {
                    // The network request might be handled in a different thread so make sure Espresso knows
                    // that the app is busy until the response is handled.
                    EspressoIdlingResource.increment(); // App is busy until further notice
                })
                .doFinally(() -> {
                    // https://github.com/VisheshVadhera/PlacementApp/blob/f36e8c259cbba37c1be90409016854f8c64bb8a5/app/src/main/java/com/vishesh/placement/core/useCases/BaseUseCase.java
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                })
                .subscribeWith(disposableSingleObserver);
        compositeDisposable.add(disposable);
    }
}

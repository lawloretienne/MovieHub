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
    public void clearDisposables() {
        if(compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    @Override
    public void getSearchResponse(String query, DisposableSingleObserver disposableSingleObserver) {
        Disposable disposable = searchRepository.getSearch(query)
                .compose(schedulerTransformer)
                .subscribeWith(disposableSingleObserver);
        compositeDisposable.add(disposable);
    }
}

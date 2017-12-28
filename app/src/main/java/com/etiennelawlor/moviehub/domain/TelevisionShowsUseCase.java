package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsPage;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 11/11/17.
 */

public class TelevisionShowsUseCase implements TelevisionShowsDomainContract.UseCase {

    // region Member Variables
    private final TelevisionShowDataSourceContract.Repository televisionShowRepository;
    private final SchedulerTransformer<TelevisionShowsPage> schedulerTransformer;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors
    public TelevisionShowsUseCase(TelevisionShowDataSourceContract.Repository televisionShowRepository, SchedulerTransformer<TelevisionShowsPage> schedulerTransformer) {
        this.televisionShowRepository = televisionShowRepository;
        this.schedulerTransformer = schedulerTransformer;
    }
    // endregion

    // region TelevisionShowsDomainContract.UseCase Methods
    @Override
    public void clearDisposables() {
        if(compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    @Override
    public void getPopularTelevisionShows(int currentPage, DisposableSingleObserver disposableSingleObserver) {
        Disposable disposable = televisionShowRepository.getPopularTelevisionShows(currentPage)
                .compose(schedulerTransformer)
                .subscribeWith(disposableSingleObserver);
        compositeDisposable.add(disposable);
    }
    // endregion
}

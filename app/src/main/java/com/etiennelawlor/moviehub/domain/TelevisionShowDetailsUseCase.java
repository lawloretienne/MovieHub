package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class TelevisionShowDetailsUseCase implements TelevisionShowDetailsDomainContract.UseCase {

    // region Member Variables
    private final TelevisionShowDataSourceContract.Repository televisionShowRepository;
    private final SchedulerTransformer<TelevisionShowDetailsWrapper> schedulerTransformer;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors
    public TelevisionShowDetailsUseCase(TelevisionShowDataSourceContract.Repository televisionShowRepository, SchedulerTransformer<TelevisionShowDetailsWrapper> schedulerTransformer) {
        this.televisionShowRepository = televisionShowRepository;
        this.schedulerTransformer = schedulerTransformer;
    }
    // endregion

    // region MovieDetailsDomainContract.UseCase Methods
    @Override
    public void clearDisposables() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void getTelevisionShowDetails(int televisionShowId, DisposableSingleObserver disposableSingleObserver) {
        Disposable disposable = televisionShowRepository.getTelevisionShowDetails(televisionShowId)
                .compose(schedulerTransformer)
                .subscribeWith(disposableSingleObserver);
        compositeDisposable.add(disposable);
    }
    // endregion

}

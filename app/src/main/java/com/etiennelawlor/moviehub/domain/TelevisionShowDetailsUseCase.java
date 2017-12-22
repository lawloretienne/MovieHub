package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer2;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class TelevisionShowDetailsUseCase implements TelevisionShowDetailsDomainContract.UseCase {

    // region Member Variables
    private final TelevisionShowDataSourceContract.Repository televisionShowRepository;
    private final SchedulerTransformer2<TelevisionShowDetailsWrapper> schedulerTransformer;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors
    public TelevisionShowDetailsUseCase(TelevisionShowDataSourceContract.Repository televisionShowRepository, SchedulerTransformer2<TelevisionShowDetailsWrapper> schedulerTransformer) {
        this.televisionShowRepository = televisionShowRepository;
        this.schedulerTransformer = schedulerTransformer;
    }
    // endregion

    // region MovieDetailsDomainContract.UseCase Methods
    @Override
    public void clearSubscriptions() {
        if(compositeDisposable != null && compositeDisposable.isDisposed())
            compositeDisposable.clear();
    }

    @Override
    public void getTelevisionShowDetails(int televisionShowId, DisposableSingleObserver disposableSingleObserver) {
        Disposable disposable = televisionShowRepository.getTelevisionShowDetails(televisionShowId)
                .compose(schedulerTransformer)
                .doOnSubscribe(disposable1 -> {
                    // The network request might be handled in a different thread so make sure Espresso knows
                    // that the app is busy until the response is handled.
                    EspressoIdlingResource.increment(); // App is busy until further notice
                })
                .doFinally(() -> {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                })
                .subscribeWith(disposableSingleObserver);
        compositeDisposable.add(disposable);
    }
    // endregion

}

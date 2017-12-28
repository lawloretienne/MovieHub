package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class PersonsUseCase implements PersonsDomainContract.UseCase {

    // region Member Variables
    private final PersonDataSourceContract.Repository personRepository;
    private final SchedulerTransformer<PersonsPage> schedulerTransformer;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors
    public PersonsUseCase(PersonDataSourceContract.Repository personRepository, SchedulerTransformer<PersonsPage> schedulerTransformer) {
        this.personRepository = personRepository;
        this.schedulerTransformer = schedulerTransformer;
    }
    // endregion

    // region PersonsDomainContract.UseCase Methods
    @Override
    public void clearDisposables() {
        if(compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    @Override
    public void getPopularPersons(int currentPage, DisposableSingleObserver disposableSingleObserver) {
        Disposable disposable = personRepository.getPopularPersons(currentPage)
                .compose(schedulerTransformer)
                .subscribeWith(disposableSingleObserver);
        compositeDisposable.add(disposable);
    }
    // endregion

}

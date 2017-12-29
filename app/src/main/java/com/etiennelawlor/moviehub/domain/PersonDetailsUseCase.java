package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class PersonDetailsUseCase implements PersonDetailsDomainContract.UseCase {

    // region Member Variables
    private final PersonDataSourceContract.Repository personRepository;
    private final SchedulerTransformer<PersonDetailsWrapper> schedulerTransformer;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors
    public PersonDetailsUseCase(PersonDataSourceContract.Repository personRepository, SchedulerTransformer<PersonDetailsWrapper> schedulerTransformer) {
        this.personRepository = personRepository;
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
    public void getPersonDetails(int personId, DisposableSingleObserver disposableSingleObserver) {
        Disposable disposable = personRepository.getPersonDetails(personId)
                .compose(schedulerTransformer)
                .subscribeWith(disposableSingleObserver);
        compositeDisposable.add(disposable);
    }
    // endregion

}

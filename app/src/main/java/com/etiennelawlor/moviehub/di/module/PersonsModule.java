package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.person.PersonLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRepository;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;
import com.etiennelawlor.moviehub.domain.PersonsDomainContract;
import com.etiennelawlor.moviehub.domain.PersonsUseCase;
import com.etiennelawlor.moviehub.presentation.persons.PersonsPresenter;
import com.etiennelawlor.moviehub.presentation.persons.PersonsUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class PersonsModule {

    private PersonsUiContract.View personsView;

    public PersonsModule(PersonsUiContract.View personsView) {
        this.personsView = personsView;
    }

    @Provides
    public PersonDataSourceContract.LocalDateSource providePersonLocalDataSource() {
        return new PersonLocalDataSource();
    }

    @Provides
    public PersonDataSourceContract.RemoteDateSource providePersonRemoteDataSource() {
        return new PersonRemoteDataSource();
    }

    @Provides
    public PersonDataSourceContract.Repository providePersonRepository(PersonDataSourceContract.LocalDateSource personLocalDataSource, PersonDataSourceContract.RemoteDateSource personRemoteDataSource) {
        return new PersonRepository(personLocalDataSource, personRemoteDataSource);
    }

    @Provides
    public SchedulerTransformer<PersonsPage> provideSchedulerTransformer() {
        return new ProductionSchedulerTransformer<PersonsPage>();
    }

    @Provides
    public PersonsDomainContract.UseCase providePersonsUseCase(PersonDataSourceContract.Repository personRepository, SchedulerTransformer<PersonsPage> schedulerTransformer) {
        return new PersonsUseCase(personRepository, schedulerTransformer);
    }

    @Provides
    public PersonsUiContract.Presenter providePersonsPresenter(PersonsDomainContract.UseCase personsUseCase) {
        return new PersonsPresenter(personsView, personsUseCase);
    }
}

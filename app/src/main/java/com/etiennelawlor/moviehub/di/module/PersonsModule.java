package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.person.PersonLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRepository;
import com.etiennelawlor.moviehub.domain.usecases.PersonsDomainContract;
import com.etiennelawlor.moviehub.domain.usecases.PersonsUseCase;
import com.etiennelawlor.moviehub.presentation.persons.PersonsPresentationContract;
import com.etiennelawlor.moviehub.presentation.persons.PersonsPresenter;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class PersonsModule {

    private PersonsPresentationContract.View personsView;

    public PersonsModule(PersonsPresentationContract.View personsView) {
        this.personsView = personsView;
    }

    @Provides
    public PersonDataSourceContract.LocalDateSource providePersonLocalDataSource() {
        return new PersonLocalDataSource();
    }

    @Provides
    public PersonDataSourceContract.RemoteDateSource providePersonRemoteDataSource(MovieHubService movieHubService) {
        return new PersonRemoteDataSource(movieHubService);
    }

    @Provides
    public PersonDataSourceContract.Repository providePersonRepository(PersonDataSourceContract.LocalDateSource personLocalDataSource, PersonDataSourceContract.RemoteDateSource personRemoteDataSource) {
        return new PersonRepository(personLocalDataSource, personRemoteDataSource);
    }

    @Provides
    public PersonsDomainContract.UseCase providePersonsUseCase(PersonDataSourceContract.Repository personRepository) {
        return new PersonsUseCase(personRepository);
    }

    @Provides
    public PersonsPresentationContract.Presenter providePersonsPresenter(PersonsDomainContract.UseCase personsUseCase, SchedulerProvider schedulerProvider) {
        return new PersonsPresenter(personsView, personsUseCase, schedulerProvider);
    }
}

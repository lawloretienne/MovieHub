package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.person.PersonLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRepository;
import com.etiennelawlor.moviehub.domain.usecases.PersonsDomainContract;
import com.etiennelawlor.moviehub.domain.usecases.PersonsUseCase;
import com.etiennelawlor.moviehub.presentation.persons.PersonsPresenter;
import com.etiennelawlor.moviehub.presentation.persons.PersonsUiContract;

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
    public PersonsUiContract.Presenter providePersonsPresenter(PersonsDomainContract.UseCase personsUseCase) {
        return new PersonsPresenter(personsView, personsUseCase);
    }
}

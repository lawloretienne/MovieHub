package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.person.PersonLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRepository;
import com.etiennelawlor.moviehub.domain.usecases.PersonDetailsDomainContract;
import com.etiennelawlor.moviehub.domain.usecases.PersonDetailsUseCase;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsUiContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class PersonDetailsModule {

    private PersonDetailsUiContract.View personDetailsView;

    public PersonDetailsModule(PersonDetailsUiContract.View personDetailsView) {
        this.personDetailsView = personDetailsView;
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
    public PersonDetailsDomainContract.UseCase providePersonDetailsUseCase(PersonDataSourceContract.Repository personRepository) {
        return new PersonDetailsUseCase(personRepository);
    }

    @Provides
    public PersonDetailsUiContract.Presenter providePersonDetailsPresenter(PersonDetailsDomainContract.UseCase personDetailsUseCase) {
        return new PersonDetailsPresenter(personDetailsView, personDetailsUseCase);
    }
}

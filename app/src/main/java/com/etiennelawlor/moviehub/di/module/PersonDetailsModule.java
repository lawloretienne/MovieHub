package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.person.PersonLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRepository;
import com.etiennelawlor.moviehub.domain.usecases.PersonDetailsDomainContract;
import com.etiennelawlor.moviehub.domain.usecases.PersonDetailsUseCase;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsPresentationContract;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsPresenter;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class PersonDetailsModule {

    private PersonDetailsPresentationContract.View personDetailsView;

    public PersonDetailsModule(PersonDetailsPresentationContract.View personDetailsView) {
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
    public PersonDetailsPresentationContract.Presenter providePersonDetailsPresenter(PersonDetailsDomainContract.UseCase personDetailsUseCase, SchedulerProvider schedulerProvider) {
        return new PersonDetailsPresenter(personDetailsView, personDetailsUseCase, schedulerProvider);
    }
}

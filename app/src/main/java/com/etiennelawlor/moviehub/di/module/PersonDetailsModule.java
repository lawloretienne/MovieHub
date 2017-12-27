package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.repositories.person.PersonLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRepository;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.domain.PersonDetailsUseCase;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

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
    public PersonLocalDataSource providePersonLocalDataSource() {
        return new PersonLocalDataSource();
    }

    @Provides
    public PersonRemoteDataSource providePersonRemoteDataSource() {
        return new PersonRemoteDataSource();
    }

    @Provides
    public PersonRepository providePersonRepository(PersonLocalDataSource personLocalDataSource, PersonRemoteDataSource personRemoteDataSource) {
        return new PersonRepository(personLocalDataSource, personRemoteDataSource);
    }

    @Provides
    public ProductionSchedulerTransformer<PersonDetailsWrapper> provideProductionSchedulerTransformer() {
        return new ProductionSchedulerTransformer<PersonDetailsWrapper>();
    }

    @Provides
    public PersonDetailsUseCase providePersonDetailsUseCase(PersonRepository personRepository, ProductionSchedulerTransformer<PersonDetailsWrapper> productionSchedulerTransformer) {
        return new PersonDetailsUseCase(personRepository, productionSchedulerTransformer);
    }

    @Provides
    public PersonDetailsPresenter provideMovieDetailsPresenter(PersonDetailsUseCase personDetailsUseCase) {
        return new PersonDetailsPresenter(personDetailsView, personDetailsUseCase);
    }
}

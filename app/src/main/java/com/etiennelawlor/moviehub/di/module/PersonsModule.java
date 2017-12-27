package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.repositories.person.PersonLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRepository;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;
import com.etiennelawlor.moviehub.domain.PersonsUseCase;
import com.etiennelawlor.moviehub.presentation.persons.PersonsPresenter;
import com.etiennelawlor.moviehub.presentation.persons.PersonsUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

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
    public ProductionSchedulerTransformer<PersonsPage> provideProductionSchedulerTransformer() {
        return new ProductionSchedulerTransformer<PersonsPage>();
    }

    @Provides
    public PersonsUseCase providePersonsUseCase(PersonRepository personRepository, ProductionSchedulerTransformer<PersonsPage> productionSchedulerTransformer) {
        return new PersonsUseCase(personRepository, productionSchedulerTransformer);
    }

    @Provides
    public PersonsPresenter provideMoviesPresenter(PersonsUseCase personsUseCase) {
        return new PersonsPresenter(personsView, personsUseCase);
    }
}

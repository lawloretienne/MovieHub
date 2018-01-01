package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.repositories.mappers.PersonCreditsDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.PersonDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.PersonsDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonRepository implements PersonDataSourceContract.Repository {

    // region Member Variables
    private PersonDataSourceContract.LocalDateSource personLocalDataSource;
    private PersonDataSourceContract.RemoteDateSource personRemoteDataSource;
    private PersonsDataModelMapper personsDataModelMapper = new PersonsDataModelMapper();
    private PersonDataModelMapper personDataModelMapper = new PersonDataModelMapper();
    private PersonCreditsDataModelMapper personCreditsDataModelMapper = new PersonCreditsDataModelMapper();
    // endregion

    // region Constructors
    public PersonRepository(PersonDataSourceContract.LocalDateSource personLocalDataSource, PersonDataSourceContract.RemoteDateSource personRemoteDataSource) {
        this.personLocalDataSource = personLocalDataSource;
        this.personRemoteDataSource = personRemoteDataSource;
    }
    // endregion

    // region PersonDataSourceContract.Repository Methods
    @Override
    public Single<PersonsDataModel> getPopularPersons(final int currentPage) {
        Maybe<PersonsDataModel> local = personLocalDataSource.getPopularPersons(currentPage)
                .filter(personsDataModel -> !personsDataModel.isExpired());
        Single<PersonsDataModel> remote =
                personRemoteDataSource.getPopularPersons(currentPage)
                        .map(personsResponse -> personsDataModelMapper.mapToDataModel(personsResponse))
                        .doOnSuccess(personsDataModel -> personLocalDataSource.savePopularPersons(personsDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<PersonDataModel> getPerson(int personId) {
        Maybe<PersonDataModel> local = personLocalDataSource.getPerson(personId);
        Single<PersonDataModel> remote =
                personRemoteDataSource.getPerson(personId)
                        .map(personResponse -> personDataModelMapper.mapToDataModel(personResponse))
                        .doOnSuccess(personDataModel -> personLocalDataSource.savePerson(personDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<PersonCreditsDataModel> getPersonCredits(int personId) {
        Maybe<PersonCreditsDataModel> local = personLocalDataSource.getPersonCredits(personId);
        Single<PersonCreditsDataModel> remote =
                personRemoteDataSource.getPersonCredits(personId)
                        .map(personCreditsResponse -> personCreditsDataModelMapper.mapToDataModel(personCreditsResponse))
                        .doOnSuccess(personCreditsDataModel -> personLocalDataSource.savePersonCredits(personCreditsDataModel));

        return local.switchIfEmpty(remote);
    }

    // endregion
}

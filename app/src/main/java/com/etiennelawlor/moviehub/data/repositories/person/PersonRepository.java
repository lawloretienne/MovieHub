package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditsResponse;
import com.etiennelawlor.moviehub.data.repositories.mappers.PersonsDataModelMapper;
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
    public Single<Person> getPerson(int personId) {
        Maybe<Person> local = personLocalDataSource.getPerson(personId);
        Single<Person> remote =
                personRemoteDataSource.getPerson(personId)
                        .doOnSuccess(person -> personLocalDataSource.savePerson(person));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<PersonCreditsResponse> getPersonCredits(int personId) {
        Maybe<PersonCreditsResponse> local = personLocalDataSource.getPersonCredits(personId);
        Single<PersonCreditsResponse> remote =
                personRemoteDataSource.getPersonCredits(personId)
                        .doOnSuccess(personCreditsEnvelope -> personLocalDataSource.savePersonCredits(personCreditsEnvelope));

        return local.switchIfEmpty(remote);
    }

    // endregion
}

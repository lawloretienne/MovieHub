package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditsEnvelope;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;

import java.util.Calendar;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonRepository implements PersonDataSourceContract.Repository {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    // region Member Variables
    private PersonDataSourceContract.LocalDateSource personLocalDataSource;
    private PersonDataSourceContract.RemoteDateSource personRemoteDataSource;
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
                        .flatMap(peopleEnvelope -> Single.just(peopleEnvelope.getPersons()))
                        .map(persons -> {
                            boolean isLastPage = persons.size() < PAGE_SIZE ? true : false;
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.DATE, SEVEN_DAYS);
                            return new PersonsDataModel(persons, currentPage, isLastPage, calendar.getTime() );
                        })
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
    public Single<PersonCreditsEnvelope> getPersonCredits(int personId) {
        Maybe<PersonCreditsEnvelope> local = personLocalDataSource.getPersonCredits(personId);
        Single<PersonCreditsEnvelope> remote =
                personRemoteDataSource.getPersonCredits(personId)
                        .doOnSuccess(personCreditsEnvelope -> personLocalDataSource.savePersonCredits(personCreditsEnvelope));

        return local.switchIfEmpty(remote);
    }

    // endregion
}

package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.repositories.person.models.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;

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
    public Single<PersonsPage> getPopularPersons(final int currentPage) {
        Maybe<PersonsPage> local = personLocalDataSource.getPopularPersons(currentPage)
                .filter(personsPage -> !personsPage.isExpired());
        Single<PersonsPage> remote =
                personRemoteDataSource.getPopularPersons(currentPage)
                        .flatMap(peopleEnvelope -> Single.just(peopleEnvelope.getPersons()))
                        .map(persons -> {
                            boolean isLastPage = persons.size() < PAGE_SIZE ? true : false;
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.DATE, SEVEN_DAYS);
                            return new PersonsPage(persons, currentPage, isLastPage, calendar.getTime() );
                        })
                        .doOnSuccess(personsPage -> personLocalDataSource.savePopularPersons(personsPage));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<PersonDetailsWrapper> getPersonDetails(int personId) {
        Maybe<PersonDetailsWrapper> local = personLocalDataSource.getPersonDetails(personId);
        Single<PersonDetailsWrapper> remote =
                personRemoteDataSource.getPersonDetails(personId)
                        .doOnSuccess(personDetailsWrapper -> personLocalDataSource.savePersonDetails(personDetailsWrapper));

        return local.switchIfEmpty(remote);
    }
    // endregion
}

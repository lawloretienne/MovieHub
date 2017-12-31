package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;
import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class PersonsUseCase implements PersonsDomainContract.UseCase {

    // region Member Variables
    private final PersonDataSourceContract.Repository personRepository;
    // endregion

    // region Constructors
    public PersonsUseCase(PersonDataSourceContract.Repository personRepository) {
        this.personRepository = personRepository;
    }
    // endregion

    // region PersonsDomainContract.UseCase Methods
    @Override
    public Single<PersonsDataModel> getPopularPersons(int currentPage) {
        return personRepository.getPopularPersons(currentPage);
    }
    // endregion

}

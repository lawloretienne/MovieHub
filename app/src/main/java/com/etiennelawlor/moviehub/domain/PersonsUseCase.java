package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.domain.mappers.PersonsDomainModelMapper;
import com.etiennelawlor.moviehub.domain.models.PersonsDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class PersonsUseCase implements PersonsDomainContract.UseCase {

    // region Member Variables
    private final PersonDataSourceContract.Repository personRepository;
    private PersonsDomainModelMapper personsDomainModelMapper = new PersonsDomainModelMapper();
    // endregion

    // region Constructors
    public PersonsUseCase(PersonDataSourceContract.Repository personRepository) {
        this.personRepository = personRepository;
    }
    // endregion

    // region PersonsDomainContract.UseCase Methods
    @Override
    public Single<PersonsDomainModel> getPopularPersons(int currentPage) {
        return personRepository.getPopularPersons(currentPage)
                .map(personsDataModel -> personsDomainModelMapper.mapToDomainModel(personsDataModel));
    }
    // endregion

}

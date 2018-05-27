package com.etiennelawlor.moviehub.domain.usecases;

import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.domain.composers.PersonDetailsDomainModelComposer;
import com.etiennelawlor.moviehub.domain.models.PersonDetailsDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class PersonDetailsUseCase implements PersonDetailsDomainContract.UseCase {

    // region Member Variables
    private final PersonDataSourceContract.Repository personRepository;
    private final PersonDetailsDomainModelComposer personDetailsDomainModelComposer = new PersonDetailsDomainModelComposer();
    // endregion

    // region Constructors
    public PersonDetailsUseCase(PersonDataSourceContract.Repository personRepository) {
        this.personRepository = personRepository;
    }
    // endregion

    // region MovieDetailsDomainContract.UseCase Methods
    @Override
    public Single<PersonDetailsDomainModel> getPersonDetails(int personId) {
        return Single.zip(
                personRepository.getPerson(personId),
                personRepository.getPersonCredits(personId),
                personDetailsDomainModelComposer::compose);
    }
    // endregion

}

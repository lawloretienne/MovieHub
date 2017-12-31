package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.network.response.PersonCredit;
import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.domain.models.PersonDetailsDomainModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class PersonDetailsUseCase implements PersonDetailsDomainContract.UseCase {

    // region Member Variables
    private final PersonDataSourceContract.Repository personRepository;
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
                (person, personCreditsEnvelope) -> {
                    List<PersonCredit> cast = new ArrayList<>();
                    List<PersonCredit> crew = new ArrayList<>();

                    if(personCreditsEnvelope!=null){
                        cast = personCreditsEnvelope.getCast();
                    }

                    if(personCreditsEnvelope!=null){
                        crew = personCreditsEnvelope.getCrew();
                    }

                    return new PersonDetailsDomainModel(person, cast, crew);
                });
    }
    // endregion

}

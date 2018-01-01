package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditDataModel;
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
                (personDataModel, personCreditsDataModel) -> {
                    List<PersonCreditDataModel> cast = new ArrayList<>();
                    List<PersonCreditDataModel> crew = new ArrayList<>();

                    if(personCreditsDataModel!=null){
                        cast = personCreditsDataModel.getCast();
                    }

                    if(personCreditsDataModel!=null){
                        crew = personCreditsDataModel.getCrew();
                    }

                    return new PersonDetailsDomainModel(personDataModel, cast, crew);
                });
    }
    // endregion

}

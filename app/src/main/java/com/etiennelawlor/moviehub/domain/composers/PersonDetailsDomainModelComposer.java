package com.etiennelawlor.moviehub.domain.composers;

import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;
import com.etiennelawlor.moviehub.domain.mappers.PersonCreditDomainModelMapper;
import com.etiennelawlor.moviehub.domain.mappers.PersonDomainModelMapper;
import com.etiennelawlor.moviehub.domain.models.PersonDetailsDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonDetailsDomainModelComposer {

    private PersonCreditDomainModelMapper personCreditDomainModelMapper = new PersonCreditDomainModelMapper();
    private PersonDomainModelMapper personDomainModelMapper = new PersonDomainModelMapper();

    public PersonDetailsDomainModel compose(PersonDataModel personDataModel, PersonCreditsDataModel personCreditsDataModel){
        PersonDetailsDomainModel personDetailsDomainModel = new PersonDetailsDomainModel();
        List<PersonCreditDataModel> cast = new ArrayList<>();
        List<PersonCreditDataModel> crew = new ArrayList<>();

        if(personCreditsDataModel!=null){
            cast = personCreditsDataModel.getCast();
        }

        if(personCreditsDataModel!=null){
            crew = personCreditsDataModel.getCrew();
        }
        personDetailsDomainModel.setCast(personCreditDomainModelMapper.mapListToDomainModelList(cast));
        personDetailsDomainModel.setCrew(personCreditDomainModelMapper.mapListToDomainModelList(crew));
        personDetailsDomainModel.setPerson(personDomainModelMapper.mapToDomainModel(personDataModel));
        return personDetailsDomainModel;
    }
}

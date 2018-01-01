package com.etiennelawlor.moviehub.domain.composers;

import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;
import com.etiennelawlor.moviehub.domain.models.PersonDetailsDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonDetailsDomainModelComposer {
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
        personDetailsDomainModel.setCast(cast);
        personDetailsDomainModel.setCrew(crew);
        personDetailsDomainModel.setPerson(personDataModel);
        return personDetailsDomainModel;
    }
}

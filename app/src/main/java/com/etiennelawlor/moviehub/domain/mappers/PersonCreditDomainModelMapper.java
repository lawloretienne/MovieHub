package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.network.response.PersonCreditResponse;
import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditDataModel;
import com.etiennelawlor.moviehub.domain.models.PersonCreditDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonCreditDomainModelMapper implements DomainModelMapper<PersonCreditDataModel, PersonCreditDomainModel>, DomainModelListMapper<PersonCreditDataModel, PersonCreditDomainModel> {

    @Override
    public PersonCreditDomainModel mapToDomainModel(PersonCreditDataModel personCreditDataModel) {
        PersonCreditDomainModel personCreditDomainModel = new PersonCreditDomainModel();
        personCreditDomainModel.setCharacter(personCreditDataModel.getCharacter());
        personCreditDomainModel.setDepartment(personCreditDataModel.getDepartment());
        personCreditDomainModel.setFirstAirDate(personCreditDataModel.getFirstAirDate());
        personCreditDomainModel.setJob(personCreditDataModel.getJob());
        personCreditDomainModel.setMediaType(personCreditDataModel.getMediaType());
        personCreditDomainModel.setName(personCreditDataModel.getName());
        personCreditDomainModel.setPosterPath(personCreditDataModel.getPosterPath());
        personCreditDomainModel.setReleaseDate(personCreditDataModel.getReleaseDate());
        personCreditDomainModel.setTitle(personCreditDataModel.getTitle());
        personCreditDomainModel.setCreditId(personCreditDataModel.getCreditId());
        personCreditDomainModel.setId(personCreditDataModel.getId());
        return personCreditDomainModel;
    }

    @Override
    public List<PersonCreditDomainModel> mapListToDomainModelList(List<PersonCreditDataModel> personCreditDataModels) {
        List<PersonCreditDomainModel> personCreditDomainModels = new ArrayList<>();
        if(personCreditDataModels != null && personCreditDataModels.size()>0) {
            for (PersonCreditDataModel personCreditDataModel : personCreditDataModels) {
                personCreditDomainModels.add(mapToDomainModel(personCreditDataModel));
            }
        }
        return personCreditDomainModels;
    }
}

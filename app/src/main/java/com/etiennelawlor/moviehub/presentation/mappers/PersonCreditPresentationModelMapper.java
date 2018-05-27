package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.PersonCreditDomainModel;
import com.etiennelawlor.moviehub.presentation.models.PersonCreditPresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonCreditPresentationModelMapper implements PresentationModelMapper<PersonCreditDomainModel, PersonCreditPresentationModel>, PresentationModelListMapper<PersonCreditDomainModel, PersonCreditPresentationModel> {

    @Override
    public PersonCreditPresentationModel mapToPresentationModel(PersonCreditDomainModel personCreditDomainModel) {
        PersonCreditPresentationModel personCreditPresentationModel = new PersonCreditPresentationModel();
        personCreditPresentationModel.setCharacter(personCreditDomainModel.getCharacter());
        personCreditPresentationModel.setDepartment(personCreditDomainModel.getDepartment());
        personCreditPresentationModel.setFirstAirDate(personCreditDomainModel.getFirstAirDate());
        personCreditPresentationModel.setJob(personCreditDomainModel.getJob());
        personCreditPresentationModel.setMediaType(personCreditDomainModel.getMediaType());
        personCreditPresentationModel.setName(personCreditDomainModel.getName());
        personCreditPresentationModel.setPosterPath(personCreditDomainModel.getPosterPath());
        personCreditPresentationModel.setReleaseDate(personCreditDomainModel.getReleaseDate());
        personCreditPresentationModel.setTitle(personCreditDomainModel.getTitle());
        personCreditPresentationModel.setCreditId(personCreditDomainModel.getCreditId());
        personCreditPresentationModel.setId(personCreditDomainModel.getId());
        return personCreditPresentationModel;
    }

    @Override
    public List<PersonCreditPresentationModel> mapListToPresentationModelList(List<PersonCreditDomainModel> personCreditDomainModels) {
        List<PersonCreditPresentationModel> personCreditPresentationModels = new ArrayList<>();
        if(personCreditDomainModels != null && personCreditDomainModels.size()>0) {
            for (PersonCreditDomainModel personCreditDomainModel : personCreditDomainModels) {
                personCreditPresentationModels.add(mapToPresentationModel(personCreditDomainModel));
            }
        }
        return personCreditPresentationModels;
    }
}

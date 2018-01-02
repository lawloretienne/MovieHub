package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.PersonDetailsDomainModel;
import com.etiennelawlor.moviehub.presentation.models.PersonDetailsPresentationModel;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonDetailsPresentationModelMapper implements PresentationModelMapper<PersonDetailsDomainModel, PersonDetailsPresentationModel> {

    private PersonCreditPresentationModelMapper personCreditPresentationModelMapper = new PersonCreditPresentationModelMapper();
    private PersonPresentationModelMapper personPresentationModelMapper = new PersonPresentationModelMapper();

    @Override
    public PersonDetailsPresentationModel mapToPresentationModel(PersonDetailsDomainModel personDetailsDomainModel) {
        PersonDetailsPresentationModel personDetailsPresentationModel = new PersonDetailsPresentationModel();
        personDetailsPresentationModel.setCast(personCreditPresentationModelMapper.mapListToPresentationModelList(personDetailsDomainModel.getCast()));
        personDetailsPresentationModel.setCrew(personCreditPresentationModelMapper.mapListToPresentationModelList(personDetailsDomainModel.getCrew()));
        personDetailsPresentationModel.setPerson(personPresentationModelMapper.mapToPresentationModel(personDetailsDomainModel.getPerson()));
        return personDetailsPresentationModel;
    }
}

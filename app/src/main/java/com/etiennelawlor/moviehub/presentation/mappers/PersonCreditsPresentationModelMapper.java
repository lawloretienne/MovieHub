package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.PersonCreditsDomainModel;
import com.etiennelawlor.moviehub.presentation.models.PersonCreditsPresentationModel;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonCreditsPresentationModelMapper implements PresentationModelMapper<PersonCreditsDomainModel, PersonCreditsPresentationModel> {

    // region Member Variables
    private PersonCreditPresentationModelMapper personCreditPresentationModelMapper = new PersonCreditPresentationModelMapper();
    // endregion

    @Override
    public PersonCreditsPresentationModel mapToPresentationModel(PersonCreditsDomainModel personCreditsDomainModel) {
        PersonCreditsPresentationModel personCreditsPresentationModel = new PersonCreditsPresentationModel();
        personCreditsPresentationModel.setCast(personCreditPresentationModelMapper.mapListToPresentationModelList(personCreditsDomainModel.getCast()));
        personCreditsPresentationModel.setCrew(personCreditPresentationModelMapper.mapListToPresentationModelList(personCreditsDomainModel.getCrew()));
        personCreditsPresentationModel.setId(personCreditsDomainModel.getId());
        return personCreditsPresentationModel;
    }
}

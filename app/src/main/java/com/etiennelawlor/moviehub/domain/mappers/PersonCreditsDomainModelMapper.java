package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.network.response.PersonCreditsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditsDataModel;
import com.etiennelawlor.moviehub.domain.models.PersonCreditsDomainModel;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonCreditsDomainModelMapper implements DomainModelMapper<PersonCreditsDataModel, PersonCreditsDomainModel> {

    // region Member Variables
    private PersonCreditDomainModelMapper personCreditDomainModelMapper = new PersonCreditDomainModelMapper();
    // endregion

    @Override
    public PersonCreditsDomainModel mapToDomainModel(PersonCreditsDataModel personCreditsDataModel) {
        PersonCreditsDomainModel personCreditsDomainModel = new PersonCreditsDomainModel();
        personCreditsDomainModel.setCast(personCreditDomainModelMapper.mapListToDomainModelList(personCreditsDataModel.getCast()));
        personCreditsDomainModel.setCrew(personCreditDomainModelMapper.mapListToDomainModelList(personCreditsDataModel.getCrew()));
        personCreditsDomainModel.setId(personCreditsDataModel.getId());
        return personCreditsDomainModel;
    }
}

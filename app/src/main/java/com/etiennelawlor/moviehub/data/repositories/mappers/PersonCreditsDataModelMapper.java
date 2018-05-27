package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.PersonCreditsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditsDataModel;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonCreditsDataModelMapper implements DataModelMapper<PersonCreditsResponse, PersonCreditsDataModel> {

    // region Member Variables
    private PersonCreditDataModelMapper personCreditDataModelMapper = new PersonCreditDataModelMapper();
    // endregion

    @Override
    public PersonCreditsDataModel mapToDataModel(PersonCreditsResponse personCreditsResponse) {
        PersonCreditsDataModel personCreditsDataModel = new PersonCreditsDataModel();
        personCreditsDataModel.setCast(personCreditDataModelMapper.mapListToDataModelList(personCreditsResponse.getCast()));
        personCreditsDataModel.setCrew(personCreditDataModelMapper.mapListToDataModelList(personCreditsResponse.getCrew()));
        personCreditsDataModel.setId(personCreditsResponse.getId());
        return personCreditsDataModel;
    }
}

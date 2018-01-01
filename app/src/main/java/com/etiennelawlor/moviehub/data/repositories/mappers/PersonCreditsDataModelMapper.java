package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.PersonCreditResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditsDataModel;

import java.util.ArrayList;
import java.util.List;

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

        List<PersonCreditResponse> castPersonCreditResponses = personCreditsResponse.getCast();
        List<PersonCreditDataModel> castPersonCreditDataModels = new ArrayList<>();
        if(castPersonCreditResponses != null && castPersonCreditResponses.size()>0) {
            for (PersonCreditResponse personCreditResponse : castPersonCreditResponses) {
                castPersonCreditDataModels.add(personCreditDataModelMapper.mapToDataModel(personCreditResponse));
            }
        }
        personCreditsDataModel.setCast(castPersonCreditDataModels);

        List<PersonCreditResponse> crewPersonCreditResponses = personCreditsResponse.getCrew();
        List<PersonCreditDataModel> crewPersonCreditDataModels = new ArrayList<>();
        if(crewPersonCreditResponses != null && crewPersonCreditResponses.size()>0) {
            for (PersonCreditResponse personCreditResponse : crewPersonCreditResponses) {
                crewPersonCreditDataModels.add(personCreditDataModelMapper.mapToDataModel(personCreditResponse));
            }
        }
        personCreditsDataModel.setCrew(castPersonCreditDataModels);
        personCreditsDataModel.setId(personCreditsResponse.getId());
        return personCreditsDataModel;
    }
}

package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.PersonCreditResponse;
import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonCreditDataModelMapper implements DataModelMapper<PersonCreditResponse, PersonCreditDataModel>, DataModelListMapper<PersonCreditResponse, PersonCreditDataModel> {

    @Override
    public PersonCreditDataModel mapToDataModel(PersonCreditResponse personCreditResponse) {
        PersonCreditDataModel personCreditDataModel = new PersonCreditDataModel();
        personCreditDataModel.setCharacter(personCreditResponse.getCharacter());
        personCreditDataModel.setDepartment(personCreditResponse.getDepartment());
        personCreditDataModel.setFirstAirDate(personCreditResponse.getFirstAirDate());
        personCreditDataModel.setJob(personCreditResponse.getJob());
        personCreditDataModel.setMediaType(personCreditResponse.getMediaType());
        personCreditDataModel.setName(personCreditResponse.getName());
        personCreditDataModel.setPosterPath(personCreditResponse.getPosterPath());
        personCreditDataModel.setReleaseDate(personCreditResponse.getReleaseDate());
        personCreditDataModel.setTitle(personCreditResponse.getTitle());
        personCreditDataModel.setCreditId(personCreditResponse.getCreditId());
        personCreditDataModel.setId(personCreditResponse.getId());
        return personCreditDataModel;
    }

    @Override
    public List<PersonCreditDataModel> mapListToDataModelList(List<PersonCreditResponse> personCreditResponses) {
        List<PersonCreditDataModel> personCreditDataModels = new ArrayList<>();
        if(personCreditResponses != null && personCreditResponses.size()>0) {
            for (PersonCreditResponse personCreditResponse : personCreditResponses) {
                personCreditDataModels.add(mapToDataModel(personCreditResponse));
            }
        }
        return personCreditDataModels;
    }
}
